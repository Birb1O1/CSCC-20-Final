public class User 
{
    private String name;
    private String gender;
    private long height;
    private long weight;
   

    //Getters setters

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public void setWeight(long weight)
    {
        this.weight = weight;
    }

    public String getName() 
    {
        return name;
    }

    public String getGender()
    {
        return gender;
    }
    
    public long getHeight()
    {
        return height;
    }

    public long getWeight()
    {
        return weight;
    }
}
