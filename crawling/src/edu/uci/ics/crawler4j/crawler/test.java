package edu.uci.ics.crawler4j.crawler;

import java.util.ArrayList;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> a=new ArrayList<String>();
		String[] b={"is","eastern","hi","bye"};
		String text="Ficus obliqua, commonly known as the small-leaved fig, is a tree native to eastern Australia, New Guinea, eastern Indonesia to Sulawesi and islands in the southwestern Pacific Ocean. It is a banyan of the genus Ficus, which contains around 750 species worldwide in warm climates, including the edible fig. Beginning life as a seedling, which grows on other plants (epiphyte) or on rocks (lithophyte), F. obliqua can grow to 60 m (200 ft) high and nearly as wide with a pale grey buttressed trunk, and glossy green leaves. The small round yellow fruit ripen and turn red at any time of year, although they peak in autumn and winter (April to July). Known as a syconium, the fruit is an inverted inflorescence with the flowers aligning an internal cavity. F. obliqua is pollinated by two species of fig wasp—Pleistodontes greenwoodi and P. xanthocephalus. Many species of bird, including pigeons, parrots and various passerines, eat the fruit. It is used as a shade tree in parks and public spaces, and is well-suited for use as an indoor plant or in bonsai. All parts of the tree have been used in traditional medicine in Fiji.Recently featured: Kellie Loder – The Diary of a Nobody – Ex parte Crow Dog";
		
		for(String my:b)
		{
			if(text.contains(my))
			{
				 a.add(my);
			}
		
}
		 System.out.println(a);

	}

}
