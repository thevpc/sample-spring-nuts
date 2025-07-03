package net.thevpc.samples.springnuts;

import net.thevpc.nuts.NApp;
import net.thevpc.nuts.NApplication;
import net.thevpc.nuts.NWorkspace;
import net.thevpc.nuts.io.NPrintStream;
import net.thevpc.nuts.util.NMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringNutsApplication  {
    @Autowired
    private NWorkspace workspace;
    @Autowired
    private NPrintStream out;

    public static void main(String[] args) {
        SpringApplication.run(SpringNutsApplication.class, args);
    }

    @NApp.Main
    public void run() {
        out.println("Hello ##Nuts## World!...");
        out.println(NMsg.ofC("we are running Nuts %s %s %s %s %s %s",
                workspace.getRuntimeId().getVersion(),
                workspace.getPlatform(),
                workspace.getOs(),
                workspace.getOsDist(),
                workspace.getArch(),
                workspace.getDesktopEnvironment()
        ));
    }
}
