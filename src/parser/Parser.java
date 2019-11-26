package parser;

import java.util.List;

public interface Parser {
    List<Item> parse(String url);
}
