public class Main {
    public static void main(String[] args) {
        MySQLDatabaseManager.initializeDatabase();
        PlacementSystem ps = new PlacementSystem();

        ps.registerStudent("S001", "Alice", "CSE", 3);
        ps.registerStudent("S002", "Bob", "ECE", 3);

        ps.registerCompany("C001", "TechCorp", "Software Engineer", "CSE", 5);
        ps.registerCompany("C002", "ElectroInc", "Hardware Engineer", "ECE", 3);

        ps.applyForCompany("S001", "C001");
        ps.applyForCompany("S002", "C002");

        ps.selectStudent("S001", "C001");

        System.out.println("Alice applications: " + ps.viewApplications("S001"));
        System.out.println("Students selected for TechCorp: " + ps.viewSelectedStudents("C001"));
    }
}
