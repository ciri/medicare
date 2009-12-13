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
            for (int i=0;i<3;i++) {
                String toadd = "gp"+i;
                if(gpRemote.addGP(toadd, toadd))
                    out.print("Creating gp "+i+"<br/>");
                else
                    out.print("Error creating gp "+i+"<br/>");
            }
            for (int i=0;i<10;i++) {
                String toadd = "patient"+i;
                String ssn = "00"+i+"-000000-"+i;
                if(gpRemote.createPatient("gp"+(i%3),ssn,toadd, toadd))
                    out.print("Creating patient "+i+"<br/>");
                else
                    out.print("Error creating patient "+i+"<br/>");
            }

            //Geef patient een taak
            MedicationDetails   md  = new MedicationDetails("Neurofen 100","200","100");
            MedicationDetails   md2 = new MedicationDetails("Neurofen 500","500","500");
            PrescriptionDetails pd  = new PrescriptionDetails("1","2","13-12-2009","15-12-2009","true",md.getName());
            PrescriptionDetails pd2 = new PrescriptionDetails("1","2","13-12-2009","15-12-2009","true",md2.getName());
            gpRemote.addMedication(md);
            gpRemote.addMedication(md2);
            gpRemote.addPrescription( "patient0", pd);
            gpRemote.addPrescription( "patient0", pd2);
        %>