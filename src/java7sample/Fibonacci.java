/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java7sample;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author TanakaTa
 */
public class Fibonacci {
    
    static class FibonacciTask extends RecursiveTask<Integer>  {
        
        private final int n;

        FibonacciTask(int n) {
            this.n = n; 
        }

        protected Integer compute() {
            if (n <= 1) {
                    // nが0か1であれば、そのまま返す
                return n;
            }

            // n-1に対してフィボナッチ数を求める
            FibonacciTask f1 = new FibonacciTask(n - 1);
            f1.fork();

            // n-2に対してフィボナッチ数を求める
            FibonacciTask f2 = new FibonacciTask(n - 2);

            // 処理結果を足し合せて、nのフィボナッチ数とする
            return f2.compute() + f1.join();
        }
    }
    
    public static void main(String[] args) {
        // タスクの実行を行なうプール
        ForkJoinPool pool = new ForkJoinPool();

        // プールにタスクを登録して、実行する
        int result = pool.invoke(new FibonacciTask(100));
        
        System.out.println("40th Fibonacci: " + result);

    }
}
