package qltc.Entity;

public class acc {
    private int id;
    private String username;
    private String pass;
    private String email;
    
    public acc(){
    }
    public acc(int id,String username,String pass,String email){
        this.id=id;
        this.username=username;
        this.pass=pass;
        this.email=email;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getPass(){
        return pass;
    }
    public void setPass(String pass){
        this.pass=pass;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }


}
