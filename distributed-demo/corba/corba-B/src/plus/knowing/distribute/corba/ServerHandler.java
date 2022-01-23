package plus.knowing.distribute.corba;

public class ServerHandler extends ServerServicePOA {

    public String method1() {
        return "this is serverB method1 ...";
    }

    @Override
    public DTO method2(String str) {
        return new DTO(str, (short) str.length());
    }
}
