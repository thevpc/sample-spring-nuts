package net.thevpc.samples.springnuts;

import net.thevpc.nuts.NApplication;
import net.thevpc.nuts.NSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringNutsApplication implements NApplication {
    @Autowired
    private NSession session;
    
    public static void main(String[] args) {
        
        SpringApplication.run(SpringNutsApplication.class, args);
    }

    @Override
    public void run() {
        session.out().println("Hello ##Nuts## World!...");
    }
}
