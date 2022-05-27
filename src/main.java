
import com.google.gson.Gson;

public class main {
    public static void main(String[]Args){

        // Creating an object of Gson class
        Gson gson = new Gson();
        // Creating an object of Employee class
        Employee emp = new Employee();

        // Attributes
        emp.setName("John");
        emp.setId("E00101");
        emp.setDepartment("IT");
        emp.setSalary(250000.00);
        emp.setRating(7);

        // Generating json from emp object
        String empJson = gson.toJson(emp);
        System.out.println("Emp json is " + empJson);

        // Changing one of the attributes of emp object
        emp.setDepartment("Java");

        // Generating emp object from emp json
        Employee empGenerated = gson.fromJson(
                gson.toJson(emp), Employee.class);

        // Print and display the employee been generated
        System.out.println(
                "Generated employee from json is "
                + empGenerated);
    }
}

class Employee {

    // Member variables of this class
    private String id;
    private String name;
    private String department;
    private int rating;
    private double salary;

    // Member functions of this class

    // Method 1
    public String getId() {
        return id;
    }

    // Method 2
    public void setId(String id) {
        this.id = id;
    }

    // Method 3
    public String getName() {
        return name;
    }

    // Method 4
    public void setName(String name) {
        this.name = name;
    }

    // Method 5
    public String getDepartment() {
        return department;
    }

    // Method 6
    public void setDepartment(String department) {
        this.department = department;
    }

    // Method 7
    public int getRating() {
        return rating;
    }

    // Method 8
    public void setRating(int rating) {
        this.rating = rating;
    }

    // Method 9
    public double getSalary() {
        return salary;
    }

    // Method 10
    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Method  11
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name
                + ", department=" + department + ", rating="
                + rating + ", salary=" + salary + "]";
    }

}