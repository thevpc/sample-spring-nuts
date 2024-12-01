package net.thevpc.samples.springnuts.core.model.payload;

import net.thevpc.samples.springnuts.core.App;
import lombok.Data;

@Data
public class VersionResponse {
    private String version= App.API_VERSION;
}
