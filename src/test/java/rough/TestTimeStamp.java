package rough;

import java.util.Date;

public class TestTimeStamp {

    public static void main(String[] args) {
        Date d = new Date();
        String screenshootName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
    }
}
