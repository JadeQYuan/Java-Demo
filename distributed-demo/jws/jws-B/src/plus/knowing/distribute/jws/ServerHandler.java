package plus.knowing.distribute.jws;

import javax.jws.WebService;

@WebService
public class ServerHandler implements ServerService {

    @Override
    public String method1() {
        return "this is serverB method1 ...";
    }

    @Override
    public DTO method2(String str) {
        DTO dto = new DTO();
        dto.setName(str);
        dto.setCount(str.length());
        return dto;
    }
}
