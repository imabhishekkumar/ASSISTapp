package com.theworkingbros.ak.assist.download;

public class subjects {
    
    String link;   
    

    public String cse(int sem)
    {
        if(sem==1)
    {
        link="https://drive.google.com/file/d/1A49NQO772YhbTt7S8rMF3kPC6BVvBhDz/view?usp=sharing";
    }
        if(sem==2)
        {
            link="https://drive.google.com/uc?export=download&id=195axX8WCDLRwIT4FwnC8cF3CC4t8tr6E";
        }
        if(sem==3)
        {
           link="https://drive.google.com/uc?export=download&id=1wwClSdRHqfusxfBpJBxvVAPAKFUGs4IO";
        }
        if(sem==4) {
            link = "https://drive.google.com/uc?export=download&id=11x46pVRFoJ-F-eAwf-_UroRWp0lGjsf3";
        }
        if(sem==5)
        {
            link="https://drive.google.com/drive/folders/1C-C4G6KzW_yIV1zUUNGshdqK1PrCTLqG?usp=sharing";
        }
        if(sem==7)
        {
            link="";
        }
        return link;
    }
    public String mech(int sem)
    {
        if(sem==3)
        {
            link="";
        }

        if(sem==5)
        {
            link="https://drive.google.com/drive/folders/1C-C4G6KzW_yIV1zUUNGshdqK1PrCTLqG?usp=sharing";
        }
        if(sem==7)
        {
            link="";
        }
        return link;
    }

    public String download(int id,int sem)
    {   if(id==1)
        cse(sem);
    
        return link;
    }



}
