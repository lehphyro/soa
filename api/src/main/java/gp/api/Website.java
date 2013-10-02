package gp.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;
import java.net.URI;

@JsonPropertyOrder({ "id", "uri" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Website {

    @JsonProperty
    private final long id;

    @JsonProperty
    private final Gp gp;

    @JsonProperty
    @NotNull
    private final URI uri;

    @SuppressWarnings("WeakerAccess")
    public Website(@JsonProperty("uri") URI uri) {
        this(new Builder().uri(uri));
    }

    @SuppressWarnings("WeakerAccess")
    protected Website(Builder builder) {
        id = builder.id;
        gp = builder.gp;
        uri = builder.uri;
    }

    public long getId() {
        return id;
    }

    public Gp getGp() {
        return gp;
    }

    public URI getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("gp", gp)
                .add("uri", uri)
                .toString();
    }

    public static class Builder {

        long id;

        Gp gp;

        URI uri;

        public Builder() {
        }

        public Builder(Website website) {
            id = website.id;
            gp = website.gp;
            uri = website.uri;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder gp(Gp gp) {
            this.gp = gp;
            return this;
        }

        public Builder uri(URI uri) {
            this.uri = uri;
            return this;
        }

        public Website build() {
            return new Website(this);
        }
    }
}
