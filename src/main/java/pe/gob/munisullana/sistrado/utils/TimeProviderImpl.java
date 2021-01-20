package pe.gob.munisullana.sistrado.utils;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TimeProviderImpl implements TimeProvider {
    @Override
    public Date now() {
        return new Date();
    }
}
