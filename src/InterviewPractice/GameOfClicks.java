package InterviewPractice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class GameOfClicks {

    /*
    Problem Statement  :

Sahil watches TV all day and gets bored. He started playing this dumb game of identifying minimum number of inputs needed to reach a channel. As his cousin, you have to help him, but you live far from his house. So you decide to write a code that will ask Sahil for some inputs and give outputs respectively.

Here are the problems you need to keep in mind :

There are 13 buttons on his remote: 10 buttons for the numbers (0-9) to form integers denoting respective channel index, “Up channel” button and “ Down channel” button for going i +1th channel and i-1th channel from i respectively, and a “Last viewed” button to see what’s the last channel before it.
The number buttons allow you to jump directly to a specific channel (Ex: to go to channel 172 by typing 1,7,2).
If the channel which you are in is ith and that is the max channel index possible, by Up channel, you will reach the first channel possible. Same goes for the down channel button. You can go to the highest channel possible if you go down from the lowest channel possible.
Sahil can get from one channel to the next in one of the two ways.
Sahil’s parents have set some parental control on some channels on Aniruth’s television. The “Up Channel “ and “Down buttons” buttons skip these channels as these channels are not viewable.
Given a list of channels to view, the lowest channel, the highest channel, and a list of blocked channels, your program should return the minimum number of clicks necessary to get through all the shows that Anirudh would like to match.

Input Format :

First line is the lowest Channel
Second-line is the highest Channel
Followed by a number of blocked channels B,
and the next B lines contain the actual blocked channels.
Followed by the number of Channels to view V, and the next V lines contain the actual channels to view.

Sample Input 0:
1
20
2
18
19
5
15
14
17
17
17
Sample output 0:
7
     */

    public static int minClicks(int lowest, int highest, int[] blocked, int[] channelsToView) {
        Set<Integer> blockedSet = Arrays.stream(blocked).boxed().collect(Collectors.toSet());
        Arrays.sort(channelsToView);

        int totalClicks = 0;
        int currentChannel = lowest;

        for (int channel : channelsToView) {
            if (channel == currentChannel) {
                continue;
            }

            int forwardDistance = Math.min(channel - currentChannel, (highest + 1) - channel + currentChannel);
            int backwardDistance = Math.min(currentChannel - channel, channel + (highest + 1) - currentChannel);

            while (true) {
                if (forwardDistance <= backwardDistance) {
                    if (currentChannel + 1 <= highest && !blockedSet.contains(currentChannel + 1)) {
                        currentChannel++;
                        forwardDistance--;
                        totalClicks++;
                    } else {
                        break;
                    }
                } else {
                    if (currentChannel - 1 >= lowest && !blockedSet.contains(currentChannel - 1)) {
                        currentChannel--;
                        backwardDistance--;
                        totalClicks++;
                    } else {
                        break;
                    }
                }
            }
        }

        return totalClicks;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lowestChannel = scanner.nextInt();
        int highestChannel = scanner.nextInt();
        int numBlocked = scanner.nextInt();
        int[] blockedChannels = new int[numBlocked];
        for (int i = 0; i < numBlocked; i++) {
            blockedChannels[i] = scanner.nextInt();
        }
        int numToView = scanner.nextInt();
        int[] channelsToView = new int[numToView];
        for (int i = 0; i < numToView; i++) {
            channelsToView[i] = scanner.nextInt();
        }

        int result = minClicks(lowestChannel, highestChannel, blockedChannels, channelsToView);
        System.out.println(result);
    }

}
