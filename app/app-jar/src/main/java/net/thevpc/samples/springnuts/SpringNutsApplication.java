package net.thevpc.samples.springnuts;

import net.thevpc.nuts.app.NApp;
import net.thevpc.nuts.app.NAppRun;
import net.thevpc.nuts.core.NWorkspace;
import net.thevpc.nuts.io.NPrintStream;
import net.thevpc.nuts.platform.NEnv;
import net.thevpc.nuts.text.NMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@NApp
@SpringBootApplication
public class SpringNutsApplication  {
    @Autowired
    private NWorkspace workspace;
    @Autowired
    private NPrintStream out;

    public static void main(String[] args) {
        SpringApplication.run(SpringNutsApplication.class, args);
    }

    @NAppRun
    public void run() {
        out.println("Hello ##Nuts## World!...");
        NEnv environment = NEnv.of();
        out.println(NMsg.ofC("we are running Nuts %s %s %s %s %s %s",
                workspace.runtimeId().version(),
                environment.java(),
                environment.os(),
                environment.osDist(),
                environment.arch(),
                environment.desktopEnvironment()
        ));
    }
}
