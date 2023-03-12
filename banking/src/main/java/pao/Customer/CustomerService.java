package pao.Customer;

public class CustomerService {
    
    private int id = 1;

    public Natural createNatural(String firstName, String lastName){

        String id = Integer.toString(getNextId());
        Natural nat = new Natural(id, firstName, lastName);

        return nat;
    }

    public Artificial createArtificial(String companyName){

        String id = Integer.toString(getNextId());
        Artificial art = new Artificial(id, companyName);

        return art;
    }

    private int getNextId()
    {
        int id = this.id;
        this.id += 1; 

        return id;
    }
}
