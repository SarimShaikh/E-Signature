package webApp.entities.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Created by Sarim on 5/6/2020.
 */
public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ADMIN");
    }
}
