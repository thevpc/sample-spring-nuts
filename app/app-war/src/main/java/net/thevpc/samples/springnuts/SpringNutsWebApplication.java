package net.thevpc.samples.springnuts;

import net.thevpc.nuts.app.NAppDefinition;
import net.thevpc.nuts.app.NAppRunner;
import net.thevpc.nuts.core.NWorkspace;
import net.thevpc.nuts.io.NPrintStream;
import net.thevpc.nuts.platform.NEnv;
import net.thevpc.nuts.text.NMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@NAppDefinition
@SpringBootApplication
public class SpringNutsWebApplication  extends SpringBootServletInitializer {
    @Autowired
    private NWorkspace workspace;
    @Autowired
    private NPrintStream out;

    public static void main(String[] args) {
        SpringApplication.run(SpringNutsWebApplication.class, args);
    }

    @NAppRunner
    public void run() {
        out.println("Hello ##Nuts## World!...");
        NEnv environment = NEnv.of();
        out.println(NMsg.ofC("we are running Nuts %s %s %s %s %s %s",
                workspace.getRuntimeId().getVersion(),
                environment.getJava(),
                environment.getOs(),
                environment.getOsDist(),
                environment.getArch(),
                environment.getDesktopEnvironment()
        ));
    }
}
