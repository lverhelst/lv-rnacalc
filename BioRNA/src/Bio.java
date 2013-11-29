import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Bio {
	
	public void main1(String [ ] args)
	{
	   System.out.println(transcribe("AGCTGGGTTTCCCAAATGC"));
	   System.out.println(transcribe("ABCDEFGHIJKLMNOP"));
	   
	   System.out.println(translate("ACGUACGUGGGAAAUUUCCGUCA"));
	   
	   
	   System.out.println(getDNAmass("AGCTGGGTTTCCCAAATGC"));
	}
	
	/**
	 * Retrieves the linear length an RNA String
	 * @param RNA Input RNA String
	 * @return Linear Length in Angstroms
	 */
	public String getRNALinearLength(String RNA){
		RNA = RNA.toUpperCase();
		//Length between RNA Base Pairs = 0.34 nm (3.4 Angstroms)
		if(RNA.matches("[AGCT]+")){
			//Angstroms
		    DecimalFormat f = new DecimalFormat("##.00");
		    f.setGroupingSize(3);
		    f.setGroupingUsed(true);
			Double rnalengthinang =  RNA.length() * 3.4;
			//Nanometers
			Double rnalengthinnm = RNA.length() * 0.34;
			return f.format(rnalengthinang) + " Angstroms (" + f.format(rnalengthinnm) + "nm)" ;
		}else 
			return "Invalid Characters";
	}

	
	//PRovides the Atomic Mass of a DNA String
	public String getDNAmass(String DNA){	
		DNA = DNA.toUpperCase();
		/*
		 * Nucleotide is made up of:
		 * 	2 phosphate groups
		 *  2 deoxyribose sugars
		 *  2 nitrogen bases
		 * 
		 * Phosphate group
		 * PO4 = (30.97 + 4(16.00)) = 94.97 Da 
		 * 
		 * Deoxyribose sugar
		 * C5H7O3 = (5(12.01) + 7(1.01) + 3(16.00)) = 115.12 Da
		 * 
		 * Adenine
		 * C5H4N5 = (5(12.01) + 4(1.01) + 5(14.01)) = 134.14 Da
		 * 
		 * Guanine
		 * C5H4N5O = (5(12.01) + 4(1.01) + 5(14.01)) + 16.00 Da = 150.14 Da
		 * 
		 * Cytosine
		 * C4H4N3O = (4(12.01) + 4(1.01) + 3(14.01) + 16.00) = 110.11 Da
		 * 
		 * Thymine
		 * C5H5N2O2 = (5(12.01) + 5(1.01) + 2(14.01) + 2(16.00)) = 125.12 Da
		 * 
		 * AT base pair
		 * 669.44 Da
		 * 
		 * CG base pair
		 * 680.43 Da 
		 * */
		
		if(DNA.matches("[AGCT]+")){
			float mass = 0;
			for(int i = 0; i < DNA.length(); i++){
				if(DNA.charAt(i) == 'A' || DNA.charAt(i) == 'T'){
					mass += 669.44;
				}
				else{
					mass += 680.43;
				}
			}		
			return mass + " Da";
		}
		System.out.println("Invalid characters");
		return "DNAMASS ERROR: Invalid Characters";
	}
	
	
	//Translates an RNA string into Protein codes
	public String translate(String RNA){
		RNA = RNA.toUpperCase();
		Map<String,String> translations = new HashMap<String,String>();
		translations.put("UUU","F");
		translations.put("UUC","F");
		translations.put("UUA","L");
		translations.put("UUG","L");
		translations.put("UCU","S");
		translations.put("UCC","S");
		translations.put("UCA","S");
		translations.put("UCG","S");
		translations.put("UAU","Y");
		translations.put("UAC","Y");
		translations.put("UAA",".");
		translations.put("UAG",".");
		translations.put("UGU","C");
		translations.put("UGC","C");
		translations.put("UGA",".");
		translations.put("UGG","W");
		
		translations.put("CUU","L");
		translations.put("CUC","L");
		translations.put("CUA","L");
		translations.put("CUG","L");
		translations.put("CCU","P");
		translations.put("CCC","P");
		translations.put("CCA","P");
		translations.put("CCG","P");	
		translations.put("CAU","H");
		translations.put("CAC","H");
		translations.put("CAA","Q");
		translations.put("CAG","Q");
		translations.put("CGU","R");
		translations.put("CGC","R");
		translations.put("CGA","R");
		translations.put("CGG","R");
		
		translations.put("AUU","I");
		translations.put("AUC","I");
		translations.put("AUA","I");
		translations.put("AUG","M");	
		translations.put("ACU","T");
		translations.put("ACC","T");
		translations.put("ACA","T");
		translations.put("ACG","T");
		translations.put("AAU","N");
		translations.put("AAC","N");
		translations.put("AAA","K");
		translations.put("AAG","K");
		translations.put("AGU","S");
		translations.put("AGC","S");
		translations.put("AGA","R");
		translations.put("AGG","R");
		
		translations.put("GUU","V");
		translations.put("GUC","V");
		translations.put("GUA","V");
		translations.put("GUG","V");	
		translations.put("GCU","A");
		translations.put("GCC","A");
		translations.put("GCA","A");
		translations.put("GCG","A");
		translations.put("GAU","D");
		translations.put("GAC","D");
		translations.put("GAA","E");
		translations.put("GAG","E");
		translations.put("GGU","G");
		translations.put("GGC","G");
		translations.put("GGA","G");
		translations.put("GGG","G");

		if(RNA.matches("[AGCU]+")){
			String AminoAcids = "";
			
			for(int i = 0; i <= RNA.length()-3; i+=3){
				AminoAcids = AminoAcids + translations.get(RNA.substring(i, i+3));;
			}
			return AminoAcids;
		}
		System.out.println("Invalid characters");
		return "Invalid characters";
	}

	
	//Transcribes a DNA string into RNA (reverse compliment)
	private static String transcribe(String DNA){
		DNA = DNA.toUpperCase();
		Map<Character, Character> transcriptions = new HashMap<Character,Character>();
		transcriptions.put('A','U');
		transcriptions.put('G','C');
		transcriptions.put('C','G');
		transcriptions.put('T','A');
		
		if(DNA.matches("[AGCT]+")){
			String RNA = "";
			for(int i = DNA.length() -1; i >= 0; i--)
			{
				RNA = RNA + "" + transcriptions.get(DNA.charAt(i));
			}
			return RNA;
		}
		System.out.println("Invalid characters");
		return "Invalid characters";
	}
	
	//reverse transcribes an RNA String
	public String reversetranscribe(String RNA){
		RNA = RNA.toUpperCase();
		if(RNA.matches("[AGCU]+")){
			String DNA = RNA.replaceAll("U","T");
			return DNA;
		}
		System.out.println("Invalid characters");
		return "Invalid characters";
	}


}