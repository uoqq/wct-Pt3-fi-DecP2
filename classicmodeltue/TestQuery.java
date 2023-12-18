package sit.int202.classicmodeltue;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import sit.int202.classicmodeltue.entities.Employee;
import sit.int202.classicmodeltue.entities.Environment;
import sit.int202.classicmodeltue.entities.Office;

import java.util.List;

public class TestQuery {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Environment.PUNIT_NANME);
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("OFFICE.FIND_ALL");
        List<Office> officeList = query.getResultList();
        for(Office office : officeList) {
            System.out.printf("%-2s %-25s %-13s %-12s\n",
                    office.getOfficeCode(), office.getAddressLine1(), office.getCity(), office.getCountry());
        }
        System.out.println("----------------");
        for(Employee employee : (List<Employee>) em.createNamedQuery("EMPLOYEE.FIND_ALL").getResultList()) {
            System.out.printf("%4d %-10s %-12s %-15s\n",
                    employee.getEmployeeNumber(), employee.getFirstName(),
                    employee.getLastName(), employee.getJobTitle());
        }
        em.close();
    }
}
