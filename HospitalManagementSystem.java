import java.util.*;

class Patient {
    static int idCounter = 1000;
    String id, name, disease;
    int age;

    Patient(String name, int age, String disease) {
        this.id = "P" + (idCounter++);
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public void displayPatient() {
        System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Disease: " + disease);
    }
}

class Appointment {
    String patientId, doctorName, date;

    Appointment(String patientId, String doctorName, String date) {
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.date = date;
    }

    public void displayAppointment() {
        System.out.println("Patient ID: " + patientId + ", Doctor: " + doctorName + ", Date: " + date);
    }
}

class Billing {
    String patientId;
    double amount;

    Billing(String patientId, double amount) {
        this.patientId = patientId;
        this.amount = amount;
    }

    public void displayBill() {
        System.out.println("Patient ID: " + patientId + ", Bill Amount: " + amount);
    }
}

class Inventory {
    String item;
    int quantity;

    Inventory(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public void displayInventory() {
        System.out.println("Item: " + item + ", Quantity: " + quantity);
    }
}

class Staff {
    String id, name, role;

    Staff(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public void displayStaff() {
        System.out.println("ID: " + id + ", Name: " + name + ", Role: " + role);
    }
}

public class HospitalManagementSystem {
    static List<Patient> patients = new ArrayList<>();
    static List<Appointment> appointments = new ArrayList<>();
    static List<Billing> bills = new ArrayList<>();
    static List<Inventory> inventories = new ArrayList<>();
    static List<Staff> staffs = new ArrayList<>();

    public static boolean isPatientRegistered(String patientId) {
        for (Patient p : patients) {
            if (p.id.equals(patientId)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nHospital Management System");
            System.out.println("1. Register Patient");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Generate Bill");
            System.out.println("4. Manage Inventory");
            System.out.println("5. Add Staff");
            System.out.println("6. View Records");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String pname = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Disease: ");
                    String disease = sc.nextLine();
                    Patient newPatient = new Patient(pname, age, disease);
                    patients.add(newPatient);
                    System.out.println("Patient Registered Successfully! ID: " + newPatient.id);
                    break;
                case 2:
                    System.out.print("Enter Patient ID: ");
                    String apid = sc.nextLine();
                    if (!isPatientRegistered(apid)) {
                        System.out.println("Patient ID not found! Please register first.");
                        break;
                    }
                    System.out.print("Enter Doctor Name: ");
                    String docName = sc.nextLine();
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String date = sc.nextLine();
                    appointments.add(new Appointment(apid, docName, date));
                    System.out.println("Appointment Scheduled Successfully!");
                    break;
                case 3:
                    System.out.print("Enter Patient ID: ");
                    String bid = sc.nextLine();
                    if (!isPatientRegistered(bid)) {
                        System.out.println("Patient ID not found! Please register first.");
                        break;
                    }
                    System.out.print("Enter Bill Amount: ");
                    double amount = sc.nextDouble();
                    bills.add(new Billing(bid, amount));
                    System.out.println("Bill Generated Successfully!");
                    break;
                case 4:
                    System.out.print("Enter Item Name: ");
                    String item = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();
                    inventories.add(new Inventory(item, quantity));
                    System.out.println("Inventory Updated Successfully!");
                    break;
                case 5:
                    System.out.print("Enter Staff ID: ");
                    String sid = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String sname = sc.nextLine();
                    System.out.print("Enter Role: ");
                    String role = sc.nextLine();
                    staffs.add(new Staff(sid, sname, role));
                    System.out.println("Staff Added Successfully!");
                    break;
                case 6:
                    System.out.println("\nPatient Records:");
                    for (Patient p : patients) p.displayPatient();
                    System.out.println("\nAppointments:");
                    for (Appointment a : appointments) a.displayAppointment();
                    System.out.println("\nBills:");
                    for (Billing b : bills) b.displayBill();
                    System.out.println("\nInventory:");
                    for (Inventory i : inventories) i.displayInventory();
                    System.out.println("\nStaff Members:");
                    for (Staff s : staffs) s.displayStaff();
                    break;
                case 7:
                    System.out.println("Exiting System...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid Option! Try Again.");
            }
        }
    }
}
