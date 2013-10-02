package gp.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;

public class WebsiteDivulgacao extends Website {
    public WebsiteDivulgacao(@JsonProperty("uri") URI uri) {
        super(uri);
    }

    private WebsiteDivulgacao(Builder builder) {
        super(builder);
    }

    public static class Builder extends Website.Builder {
        public Builder() {
        }

        public Builder(WebsiteDivulgacao websiteDivulgacao) {
            super(websiteDivulgacao);
        }

        @Override
        public Builder id(long id) {
            super.id(id);
            return this;
        }

        @Override
        public Builder gp(Gp gp) {
            super.gp(gp);
            return this;
        }

        @Override
        public Builder uri(URI uri) {
            super.uri(uri);
            return this;
        }

        @Override
        public WebsiteDivulgacao build() {
            return new WebsiteDivulgacao(this);
        }
    }
}
