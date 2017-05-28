package com.company;

import java.util.*;
import java.io.*;
import java.lang.*;
public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n,i,j,k,rnd,temp,min,key,flag=0,count=0;
        System.out.println("Enter no of points");
        n = sc.nextInt();
        int d[] = new int[n];
        for(i=0;i<n;i++)
        {
            System.out.println("Enter value:");
            d[i] = sc.nextInt();
        }
        System.out.println("Enter no. of clusters");
        k = sc.nextInt();
        float mean[] = new float[k];
        float prevmean[] = new float[k];
        int rowcount[] = new int[k];
        for(i=0;i<k;i++)
            rowcount[i] = 0;
        for(i=0;i<k;i++)
            mean[i] = -999;
        while(count!= k)
        {
            rnd = new Random().nextInt(d.length);
            temp = d[rnd];
            for(i=0;i<k;i++)
            {
                if(mean[i] == temp)
                    break;
            }
            if(i == k)
                mean[count++] = temp;
        }
        System.out.println("Initial random means:");
        for(i=0;i<k;i++)
            System.out.print((int)mean[i]+"\t\t");
        System.out.println();
        for(i=0;i<k;i++)
            prevmean[i] = mean[i];
        int cluster[][] = new int[k][n];
        for(i=0;i<k;i++)
        {
            for(j=0;j<n;j++)
                cluster[i][j] = -1;
        }
        while(flag != 1)
        {
            for(i=0;i<n;i++)
            {
                min = 1000;
                key = 1000;
                for(j=0;j<k;j++)
                {
                    if(Math.abs((d[i] - mean[j])) < min )
                    {
                        min = (int)Math.abs((d[i] - mean[j]));
                        key = j;
                    }
                }
                cluster[key][rowcount[key]++] = d[i];
            }
            for(i=0;i<k;i++)
                mean[i] = 0;
            for(i=0;i<k;i++)
            {
                for(j=0;j<n;j++)
                {
                    if(cluster[i][j] != -1)
                        mean[i] = mean[i] + cluster[i][j];
                }
            }
            for(i=0;i<k;i++)
                mean[i] = mean[i]/rowcount[i];
            for(i=0;i<k;i++)
                rowcount[i] = 0;
            if(Arrays.equals(mean,prevmean))
                flag = 1;
            else
            {
                for(i=0;i<k;i++)
                    prevmean[i] = mean[i];
                for(i=0;i<k;i++)
                {
                    for(j=0;j<n;j++)
                       cluster[i][j] = -1;
                }
            }
        }
        for(i=0;i<k;i++)
        {
            System.out.print("Cluster "+(i+1)+" = { ");
            for(j=0;j<n;j++)
            {
                if(cluster[i][j] != -1)
                    System.out.print(cluster[i][j]+" ");
            }
            System.out.print("}");
            System.out.println();
        }
    }
}

