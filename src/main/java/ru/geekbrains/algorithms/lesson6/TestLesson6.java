package ru.geekbrains.algorithms.lesson6;

import ru.geekbrains.algorithms.base.mytree.Tree;
import ru.geekbrains.algorithms.base.mytree.TreeImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestLesson6 {
    private static final Random random = new Random();

    public static void main(String[] args) {
        int treeCount = 20;

        System.out.printf("Из %d деревьев в среднем несбалансировано:%n", treeCount);
        System.out.println("Глубина\tДиапазон\tНесбалансировано %");
        for (int i = 3; i < 10; i++) {
            System.out.printf("%d%13s%13.2f%n", i, "-25 25", calculateMeanPercent(treeCount, i, 50));
            System.out.printf("%d%15s%11.2f%n", i, "-100 100", calculateMeanPercent(treeCount, i, 200));
        }
    }

    private static List<Tree<Integer>> generateTrees(int treeCount, int depth, int dataRange) {
        int elementCount = (int) (Math.pow(2, depth) -1);
        List<Tree<Integer>> temp = new ArrayList<>();
        for (int i = 0; i < treeCount; i++) {
            Tree<Integer> tree = new TreeImpl<>(depth);
            for (int j = 0; j < elementCount; j++) {
                tree.add(random.nextInt(dataRange+1) - dataRange/2);
            }
            temp.add(tree);
        }
        return temp;
    }

    private static float calculateUnbalancedTrees(List<Tree<Integer>> trees) {
        int balancedTree = 0;
        int unbalancedTree = 0;
        for (Tree<Integer> tree : trees) {
            if (tree.isBalanced()) {
                balancedTree++;
            } else {
                unbalancedTree++;
            }
        }
        return (float) unbalancedTree / (balancedTree + unbalancedTree) * 100;
    }

    private static float calculateMeanPercent(int treeCount, int depth, int range) {
        int cycleCount = 100;
        float meanValue = 0f;
        for (int i = 0; i < cycleCount; i++) {
            meanValue += calculateUnbalancedTrees(generateTrees(treeCount, depth, range));
        }
        return meanValue / cycleCount;
    }
}
