import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to SamRank! The world's ONLY program that ranks the word rarity of Yosemite Sam! The following are Sam's sayings ranked is descending order of rarity. Yee-haw!");

        String qlName = "quotelist.txt";// ql = "quotelist"
        File qlFile = getFile(qlName);
        Scanner qlScanner = new Scanner(qlFile);
        LinkedList<String> qlList = new LinkedList<String>();

        String swName = "stopwords.txt";// sw = stopwords
        File swFile = getFile(swName);
        Scanner swScanner = new Scanner(swFile);
        LinkedList<String> swList = new LinkedList<String>();

        String wfName = "unigram_freq.txt";// wf = word frequency
        File wfFile = getFile(wfName);
        Scanner wfScanner = new Scanner(wfFile);
        LinkedList<rankedPairs> rankList = new LinkedList<rankedPairs>();//this list will contain rankedPair objects which include the word and its relative frequency (rank) 
        //rankList.add(new rankedPairs("test", 1));

        String srName = "SamRank.txt"; //sr = SamRank
        File SamRank = new File(srName);
        PrintStream samStream = new PrintStream(SamRank);
        samStream.println("The following are Sam's sayings ranked is descending order of rarity. Yee-haw!");


        while (swScanner.hasNext()){ //creates our easily checked list of stopwords as an LL
            swList.add(swScanner.next());
        }

        while(qlScanner.hasNext()){ //creates our easily checked list of words
            String[] qlTempArray = getTempArray(qlScanner.nextLine());
            addToQuoteList(qlTempArray, swList, qlList);
        }

        int ranker = 0;
        while (wfScanner.hasNext()){
            ranker ++;
            String temp = wfScanner.next().split(",")[0];
            addToRankList(temp, ranker, qlList, rankList);
        }
        Collections.reverse(rankList);
        
        while (!rankList.isEmpty()){
            rankedPairs temp = rankList.removeFirst();
            String toPrint = (temp.getString() + " is the " + temp.getRank() + " most common word in the English language.");
            System.out.println(toPrint);
            samStream.println(toPrint);
        }

        System.out.println("Hire me!");

        qlScanner.close();
        swScanner.close();
        wfScanner.close();
        samStream.close();
    }

    private static void addToRankList(String temp, int ranker, LinkedList<String> qlList,
            LinkedList<rankedPairs> rankList) {
                if (qlList.contains(temp)){
                    rankedPairs tempRP = new rankedPairs(temp, ranker);
                    rankList.add(tempRP);
                    qlList.remove(temp);
                }

    }

    private static void addToQuoteList(String[] qlTempArray, LinkedList<String> swList, LinkedList<String> qlList) {
        for (int x = 0; x < qlTempArray.length; x ++){
            if (!swList.contains(qlTempArray[x]) && !qlList.contains(qlTempArray[x])){//words are only added if they are not stopwords and they are not already in the quotelist
                qlList.add(qlTempArray[x]);
            }
        }
    }

    private static String[] getTempArray(String nextLine) {
        String stripped = nextLine.replaceAll("\\p{Punct}", "").replaceAll("“", "").replaceAll("”", "").replaceAll("…", "").replaceAll("  ", " ").replaceAll("’", "").toLowerCase();//the original text file as a number of nonstandard characters, such as the one-byte ellipsis.
        String[] temp = stripped.split(" ");
        return temp;
    }

    static File getFile(String filename) {
        File file = new File(filename);
        return file;
    }
}