package marcelos.corporation.desafioCrud.services.exeception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException (String msg){
        super(msg);
    }
}
