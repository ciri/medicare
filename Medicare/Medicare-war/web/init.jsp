<%@ page pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession"%>

<%@ page import="javax.naming.*,ejb.patient.PatientRemote,ejb.gp.GPRemote,util.PrescriptionDetails,util.MedicationDetails"%>

<%!
    private PatientRemote patientRemote = null;
    private GPRemote gpRemote = null;

    public void jspInit() {
        try {
            InitialContext ic = new InitialContext();
            patientRemote = (PatientRemote) ic.lookup(PatientRemote.class.getName());
            gpRemote = (GPRemote) ic.lookup(GPRemote.class.getName());
        } catch (Exception ex) {
            System.out.println("Couldn't create patientRemote bean." + ex.getMessage());
        }
    }

    public void jspDestroy() {
        patientRemote = null;
    }
%>

        <h1>Initialize</h1>
        <p>Als u dit pagina ziet, is de applicatie aan't initialiseren.</p>
        <%
            for (int i=0;i<10;i++) {
                String toadd = "patient"+i;
                String ssn = "00"+i+"-000000-"+i;
                if(patientRemote.addPatient(ssn,toadd, toadd))
                    out.print("Creating patient "+i+"<br/>");
                else
                    out.print("Error creating patient "+i+"<br/>");
            }
            for (int i=0;i<3;i++) {
                String toadd = "gp"+i;
                if(gpRemote.addGP(toadd, toadd))
                    out.print("Creating gp "+i+"<br/>");
                else
                    out.print("Error creating gp "+i+"<br/>");
            }

            for(int i=0;i<5;i++) {
                if(gpRemote.addPatient("gp0","gp0", "patient"+i))
                    out.print("Adding patient"+i+" to gp0<br/>");
                else
                    out.print("Error adding patient "+i+" to gp0<br/>");
             }
            //Geef patient een taak
            MedicationDetails   md = new MedicationDetails("ibuprofen","1","200");
            PrescriptionDetails pd = new PrescriptionDetails("1","2","01-01-1999","04-04-2011","true","ibuprofen");
            gpRemote.addMedication("gp0", "gp0", md);
            gpRemote.addPrescription("gp0", "gp0", "patient0", pd);
        %>

<%@include file="jspf/footer.jspf" %>