package gp.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;

public class WebsitePessoal extends Website {

    public WebsitePessoal(@JsonProperty("uri") URI uri) {
        super(uri);
    }

    private WebsitePessoal(Builder builder) {
        super(builder);
    }

    public static class Builder extends Website.Builder {

        public Builder() {
        }

        public Builder(WebsitePessoal websitePessoal) {
            super(websitePessoal);
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
        public WebsitePessoal build() {
            return new WebsitePessoal(this);
        }
    }
}
