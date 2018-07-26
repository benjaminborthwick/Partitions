package Partitions;
import java.util.*;
public class Partitions {
    private int n;
    private int[] Partition;
    public static void main(String[] args) {
        System.out.println("What number would you like to find the partitions of?");
        Partitions partition = new Partitions();
        Scanner scanner = new Scanner(System.in);
        partition.n = scanner.nextInt();
        partition.Partition = new int[partition.n / 2];
        ArrayList<int[]> PartitionMaster = partition.findPartitions(partition.n);
        ArrayList<int[]> transfer;
        for(int i=0; i < PartitionMaster.size(); i++){
            for(int j = 0; j < PartitionMaster.get(i).length; j++) {
                System.out.print(PartitionMaster.get(i)[j] + " ");
            }
            System.out.println();
            }
            int Size = PartitionMaster.size();
        System.out.println("There are " + Size + " non-one partitions in " + partition.n + ".");
        Size+=3;
        for(int i=4;i<partition.n;i++){
            Size+=partition.findPartitions(i).size();
        }
        System.out.println("There are " + Size + " partitions total in " + partition.n + ", but I won't print them all because that would be too many lines.");
    }
    private ArrayList<int[]> partitions2(int[] element){
        ArrayList<int[]> ans = new ArrayList<int[]>();
        ArrayList<int[]> transfer = new ArrayList<int[]>();
        int threes = 0;
        int twos = 0;
        int[] temp;
        for(int i = 0;i<element.length;i++){
            if(element[i]==3) {
                threes++;
            } else if (element[i]==2){
                twos++;
            }

        } for(int i = 0;i<element.length;i++) {
            if(element[i]>3){
                    transfer = partitions(element[i]);
                    for(int j=0;j<transfer.size();j++) {
                        temp = new int[element.length*2-twos-threes];
                        for(int k = 0; k < 2; k ++){
                            temp[k] = transfer.get(j)[k];

                        }
                        int count = 2;
                        for(int m=0;m<element.length;m++){
                            if(element[m]>3&&m!=i){
                                temp[count]=element[m];
                                count++;
                            }
                        }
                        ans.add(temp);
                    }
            }
        }
        int s = twos;
        int d = threes;
        for(int r = 0;r<ans.size();r++){
            twos = s;
            threes = d;
            for(int u = ans.get(r).length-threes-twos;u<ans.get(r).length;u++) {
                if (twos > 0) {
                    ans.get(r)[u] = 2;
                    twos--;
                } else if (threes > 0) {
                    ans.get(r)[u] = 3;
                    threes--;
                }
            }
            Arrays.sort(ans.get(r));
        }
        return ans;

    }
    private ArrayList<int[]> partitions(int meh) {
        ArrayList<int[]> PartitionMaster = new ArrayList<>();
        int n = 2;
        while(n<=meh/2) {
            Partition = new int[2];
            int i = 0;
            Partition[i] = n;
            Partition[(i+1)] = meh-n;
            PartitionMaster.add(Partition);
            n++;
        } return PartitionMaster;
    }
    private int[] removeZeroes(int[] withZeroes){
        int nonZeroLength=0;
        for(int i=0;i<withZeroes.length;i++){
            if(withZeroes[i]!=0){
                nonZeroLength++;
            }
        }
        int[] withoutZeroes = new int[nonZeroLength];
        int withoutZeroCount=0;
        for(int i=0;i<withZeroes.length;i++){
            if(withZeroes[i]!=0){
                withoutZeroes[withoutZeroCount]=withZeroes[i];
                withoutZeroCount++;
            }
        }
        return withoutZeroes;
    }
    private ArrayList<int[]> findPartitions(int p){
        ArrayList<int[]> PartitionMaster = partitions(p);
        PartitionMaster.add(new int[]{p});
        for(int i=0;i<PartitionMaster.size();i++){
            ArrayList<int[]> transfer = partitions2(PartitionMaster.get(i));
            for (int j=0;j<transfer.size();j++) {
                boolean isPartitionNew=true;
                for(int m=0;m<PartitionMaster.size();m++){
                    if(Arrays.equals(removeZeroes(PartitionMaster.get(m)), removeZeroes(transfer.get(j)))){
                        isPartitionNew=false;
                    }
                }
                if(isPartitionNew){
                    PartitionMaster.add(removeZeroes(transfer.get(j)));
                }
            }
        }
        return PartitionMaster;
    }
}
