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
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Lazy;

@NApp
@SpringBootApplication
public class SpringNutsWebApplication  extends SpringBootServletInitializer {
    @Autowired @Lazy
    private NWorkspace workspace;
    @Autowired @Lazy
    private NPrintStream out;

    public static void main(String[] args) {
        SpringApplication.run(SpringNutsWebApplication.class, args);
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
