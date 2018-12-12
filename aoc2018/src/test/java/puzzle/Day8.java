package puzzle;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class Day8 {

    private List<Integer> getNumbers() {
        try {
            String fileContent = IOUtils.readLines(
                    getClass().getClassLoader().getResourceAsStream("puzzle/Day8.txt"),
                    "UTF-8")
                    .stream().collect(joining("\n"));
            List<Integer> prodNumbers = Arrays.stream(fileContent.split(" "))
                    .map(Integer::new)
                    .collect(toList());
            List<Integer> testNumbers = asList(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2);

            return prodNumbers;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Integer> numbers = getNumbers();
    private int idSeq = 1;
    List<Node> nodes = null;

    @Test
    public void run() {
        nodes = composeNodes(null, 0);

        nodes.sort(comparing(n -> n.id));

        System.out.println(nodes.stream()
                .map(Node::toString)
                .collect(joining("\n")));

        int metadataSum = nodes.stream()
                .flatMap(node -> node.metadata.stream())
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Metadata sum: " + metadataSum);

        int rootSum = nodeSum(nodes.stream().filter(n -> n.parent == null).findFirst().orElse(null));
        System.out.println("Node sum: " + rootSum);
    }

    private int nodeSum(Node node) {
        if (node == null) {
            return 0;
        }

        int sum = 0;

        List<Node> children = nodes.stream()
                .filter(n -> n.parent != null && n.parent.id == node.id)
                .collect(toList());

        if (children.size() == 0) {
            sum = node.metadata.stream().mapToInt(Integer::intValue).sum();
        } else {
            List<Node> childrenByIndex = new ArrayList<>();
            for (Integer idx : node.metadata) {
                if (idx <= children.size()) {
                    Node nodeToSum = children.get(idx - 1);
                    if (nodeToSum.sum == null) {
                        nodeSum(nodeToSum);
                    }

                    sum += nodeToSum.sum;
                }
            }
        }

        node.sum = sum;

        return sum;
    }

    private List<Node> composeNodes(Node parent, int childIndex) {
        List<Node> nodes = new ArrayList<>();

        int childNodesCount = numbers.get(0);
        int metadataCount = numbers.get(1);
        numbers = numbers.subList(2, numbers.size());

        if (childNodesCount == 0) {
            List<Integer> metadata = numbers.subList(0, metadataCount);
            numbers = numbers.subList(metadataCount, numbers.size());
            nodes.add(new Node(childIndex, parent, metadata));

        }

        if (childNodesCount > 0) {
            Node parentNode = new Node(childIndex, parent, null);
            for (int i = 0; i < childNodesCount; i++) {
                nodes.addAll(composeNodes(parentNode, i + 1));
            }
            List<Integer> metadata = numbers.subList(0, metadataCount);
            numbers = numbers.subList(metadataCount, numbers.size());
            parentNode.setMetadata(metadata);
            nodes.add(parentNode);
        }

        return nodes;
    }

    public class Node {

        private int childIndex;
        private Node parent;
        @Setter
        private List<Integer> metadata = new ArrayList<>();
        private int id;
        private Integer sum;

        public Node(int childIndex, Node parent, List<Integer> metadata) {
            this.childIndex = childIndex;
            this.parent = parent;
            this.metadata = metadata;
            this.id = idSeq++;
        }

        @Override
        public String toString() {
            return "Node " + id + " (parent " + (parent != null ? (parent.id + " idx " + childIndex) : "NONE") + ", metadata "
                    + metadata + ")";
        }
    }

}