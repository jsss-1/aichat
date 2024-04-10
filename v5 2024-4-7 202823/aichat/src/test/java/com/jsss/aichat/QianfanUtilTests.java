package com.jsss.aichat;


import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.model.chat.ChatResponse;
import com.jsss.qianfan.util.QianfanUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class QianfanUtilTests {


    @Autowired
    QianfanUtil qianfanUtil;

    @Autowired
    Qianfan qianfan;


    @Test
    void testAddMessage() {
        String content="你好";
        String res = qianfanUtil.addMessage(content);
        System.out.println(res);

    }

    @Test
    void testAddMessagePlus() {
        ChatResponse response = qianfan.chatCompletion()
                //.model("ERNIE-Bot-4")  //使用model指定预置模型 默认模型是ERNIE-Bot-turbo
                .addMessage("user", "用java写一个冒泡排序")
                .addMessage("assistant","当然可以！以下是一个使用Java编写的冒泡排序算法示例：\n" +
                        "\n" +
                        "\n" +
                        "```java\n" +
                        "public class BubbleSort {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        int[] arr = {64, 34, 25, 12, 22, 11, 90};\n" +
                        "        bubbleSort(arr);\n" +
                        "        System.out.println(\"排序后的数组：\");\n" +
                        "        printArray(arr);\n" +
                        "    }\n" +
                        "\n" +
                        "    public static void bubbleSort(int[] arr) {\n" +
                        "        int n = arr.length;\n" +
                        "        for (int i = 0; i < n - 1; i++) {\n" +
                        "            for (int j = 0; j < n - i - 1; j++) {\n" +
                        "                if (arr[j] > arr[j + 1]) {\n" +
                        "                    // 交换 arr[j] 和 arr[j+1]\n" +
                        "                    int temp = arr[j];\n" +
                        "                    arr[j] = arr[j + 1];\n" +
                        "                    arr[j + 1] = temp;\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    public static void printArray(int[] arr) {\n" +
                        "        for (int i : arr) {\n" +
                        "            System.out.print(i + \" \");\n" +
                        "        }\n" +
                        "        System.out.println();\n" +
                        "    }\n" +
                        "}\n" +
                        "```\n" +
                        "这段代码定义了一个名为`BubbleSort`的类，其中包含一个`main`方法，用于测试冒泡排序算法。在`main`方法中，我们定义了一个整数数组`arr`，并调用`bubbleSort`方法对其进行排序。排序完成后，我们使用`printArray`方法打印排序后的数组。\n" +
                        "\n" +
                        "在`bubbleSort`方法中，我们使用嵌套的循环来遍历数组并比较相邻元素的大小。如果前一个元素大于后一个元素，则交换它们的位置。通过重复这个过程，我们最终将数组按升序排列。\n" +
                        "\n" +
                        "请注意，冒泡排序算法的时间复杂度为O(n^2)，其中n是数组的长度。因此，对于大型数据集，冒泡排序可能不是最有效的排序算法。")
                .addMessage("user","给上面回复的代码逐行注释")
                .temperature(0.7) // 自定义超参数
                .execute(); // 发起请求
        System.out.println(response.getResult());
    }
    /*
    好的，以下是对上面回复的代码逐行的注释：


    ```java
    // 定义一个名为BubbleSort的类
    public class BubbleSort {

        // 在类中定义一个main方法，用于测试冒泡排序算法
        public static void main(String[] args) {

            // 定义一个整数数组arr，并赋值
            int[] arr = {64, 34, 25, 12, 22, 11, 90};

            // 调用bubbleSort方法对数组进行排序
            bubbleSort(arr);

            // 打印排序后的数组
            System.out.println("排序后的数组：");
            printArray(arr);
        }

        // 在main方法中调用bubbleSort方法，对数组进行排序
        public static void bubbleSort(int[] arr) {

            // 获取数组的长度并赋值给变量n
            int n = arr.length;

            // 外层循环控制排序的轮数，这里假设数组长度为奇数，因此循环次数为n-1
            for (int i = 0; i < n - 1; i++) {

                // 内层循环控制每一轮比较的次数，这里假设每次比较两个相邻元素
                for (int j = 0; j < n - i - 1; j++) {

                    // 比较arr[j]和arr[j+1]的大小，如果前者大于后者，则交换它们的位置
                    if (arr[j] > arr[j + 1]) {
                        // 交换arr[j]和arr[j+1]的值
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }

        // 在main方法中定义一个printArray方法，用于打印数组元素
        public static void printArray(int[] arr) {
            // 使用for循环遍历数组，并打印每个元素的值
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();  // 在打印完数组后换行
        }
    }
    ```
    以上是对代码逐行的注释，希望对你有所帮助。

     */




    @Test
    void testExecuteStream() {
        String content="你好";
        qianfanUtil.executeStream(content);

    }





}
