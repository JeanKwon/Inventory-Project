public class Location 
{
	char sectionAlphabet;
	int cabinetNum;
	
	public Location(char section, int cabinet) 
	{	
		sectionAlphabet = section;
		cabinetNum = cabinet;
	}

	//sets the section of the item's storage location
	public void setSection(char newSection)
	{
		sectionAlphabet = newSection;
	}
	
	//sets the cabinet number of the item's storage location
	public void setCabnit(int newCabinet)
	{
		cabinetNum = newCabinet;
	}
	
	public String toString() 
	{
		return " " + sectionAlphabet + "-" + cabinetNum;
	}	

}
