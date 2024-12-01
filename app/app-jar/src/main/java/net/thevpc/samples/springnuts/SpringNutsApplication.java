package net.thevpc.samples.springnuts;

import net.thevpc.nuts.NApplication;
import net.thevpc.nuts.NSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("net.thevpc.samples.springnuts")
@SpringBootApplication
public class SpringNutsApplication implements NApplication {
    //@Autowired
    //private NSession session;
    
    public static void main(String[] args) {
        
        SpringApplication.run(SpringNutsApplication.class, args);
    }

    @Override
    public void run() {
        //NSession s = NSession.get();
        //s.out().println("Hello World...");
    }
}
