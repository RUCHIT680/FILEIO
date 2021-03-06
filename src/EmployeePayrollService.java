import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class EmployeePayRollService {
	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}
	private List<EmployeePayRollData> employeePayrollList;
	public EmployeePayRollService() {
	};
	public EmployeePayRollService(List<EmployeePayRollData> employeePayrollList) {
		this.employeePayrollList = employeePayrollList;
	}
	public static void main(String[] args) {
		ArrayList<EmployeePayRollData> employeePayrollList = new ArrayList<>();
		EmployeePayRollService employeePayRollService = new EmployeePayRollService(employeePayrollList);
		Scanner sc = new Scanner(System.in);
		employeePayRollService.readData(sc);
		employeePayRollService.writeData(null);
		employeePayRollService.writeData();
	}

	public void readData(Scanner sc) {
		System.out.println("Enter ID:");
		int id = sc.nextInt();
		System.out.println("Enter Name:");
		String name = sc.next();
		System.out.println("Enter Salary:");
		double salary = sc.nextDouble();
		employeePayrollList.add(new EmployeePayRollData(id, name, salary));
	}
	public void writeData(IOService ioService) {
		if (ioService.equals(com.bridgelabz.employee.EmployeePayRollService.IOService.CONSOLE_IO))
			System.out.println(employeePayrollList);
		else if (ioService.equals(com.bridgelabz.employee.EmployeePayRollService.IOService.FILE_IO))
			new EmployeePayrollFileIOService().writeData2(employeePayrollList);
	}
	public void printData(IOService ioService) {
		if(ioService.equals(com.bridgelabz.employee.EmployeePayRollService.IOService.FILE_IO))
			new com.bridgelabz.employee.EmployeePayrollFileIOService().printData();
	}
	public long countEntries(IOService ioService) {
		if(ioService.equals(com.bridgelabz.employee.EmployeePayRollService.IOService.FILE_IO))
			return new com.bridgelabz.employee.EmployeePayrollFileIOService().countEntries();
		return 0;

	}

	public static boolean readFile() {
		Path path = Paths.get("C:/Users/HP LAP/Desktop/BridgeLabz/FileIO/EmployeePayRoll/PayRollDoc.txt");
		try {
			String fileContent = Files.readString(path);
			String []employees = fileContent.split(",");
			for(String employee:employees)
				System.out.println(employee);
			return true;
		} catch (IOException e) {
			System.out.println(" directory not found");
		}
		return false;
	}
}