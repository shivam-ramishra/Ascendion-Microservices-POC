package base.exception;

public class ClientNotFoundException extends Exception{
    private int id;
    public ClientNotFoundException(int id){
        this.id = id;
    }
    public String getMessage(){
        return "Client with id " + id + " not found";
    }
}
