/**
 * Node module
 */
module Node {
    requires lombok;
    requires org.json;
    requires java.desktop;
    requires org.slf4j;
    exports Node;
    exports Node.Graph;
}