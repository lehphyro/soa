package gp.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;

public class WebsiteForum extends Website {
    public WebsiteForum(@JsonProperty("uri") URI uri) {
        super(uri);
    }

    private WebsiteForum(Builder builder) {
        super(builder);
    }

    public static class Builder extends Website.Builder {
        public Builder() {
        }

        public Builder(WebsiteForum websiteForum) {
            super(websiteForum);
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
        public WebsiteForum build() {
            return new WebsiteForum(this);
        }
    }
}
