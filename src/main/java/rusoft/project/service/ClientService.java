package rusoft.project.service;

public interface ClientService {

    public void addClient(String clientName, Long birthYear, String carBrand, Long manufactureYear);

    public void removeClient(String clientName, String carBrand);
}
