package com.huawei.imp.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

/**
 * 编码解析工具
 * 根据byte特性，判断byte的编码类型。
 * @author ahli
 * @version IMPV100R001DA0, Aug 6, 2009 
 * @since CMS IMPV100R001DA0
 */
public class EncodingDetectUtils
{

	/**
	 * 编码类型枚举
	 * @author ahli
	 * @version IMPV100R001DA0, Aug 6, 2009 
	 * @since CMS IMPV100R001DA0
	 */
	public enum EncodingType {
		GB2312("GB2312"),
		GBK("GBK"),
		HZ("HZ"),
		BIG5("Big5"),
		EUC_TW("CNS 11643"),
		ISO_2022_CN("ISO 2022-CN"),
		UTF8("UTF-8"),
		UNICODE("Unicode"),
		ASCII("ASCII"),
		OTHER("OTHER");
		
		private String name;
		
		private EncodingType(String name){
			this.name = name;
		}
		public String getName()
		{
			return name;
		}
	}

	// Frequency tables to hold the GB, Big5, and EUC-TW character
	// frequencies
	int GBFreq[][];

	int GBKFreq[][];

	int Big5Freq[][];

	int EUC_TWFreq[][];

	// int UnicodeFreq[94][128];

	/**
	 * 单例
	 */
	private static final EncodingDetectUtils instance = new EncodingDetectUtils();
	
	/**
	 * 返回实例
	 * @return
	 */
	public static final EncodingDetectUtils getInstance(){
		return instance;
	}
	
	/**
	 * 私有构造函数
	 */
	private EncodingDetectUtils()
	{
		// Initialize the Frequency Table for GB, Big5, EUC-TW
		GBFreq = new int[94][94];
		GBKFreq = new int[126][191];
		Big5Freq = new int[94][158];
		EUC_TWFreq = new int[94][94];
		initialize_frequencies();
	}

	/**
	 * Function : detectEncoding Aruguments: URL Returns : One of the encodings
	 * from the Encoding enumeration (GB2312, HZ, BIG5, EUC_TW, ASCII, or OTHER)
	 * Description: This function looks at the URL contents and assigns it a
	 * probability score for each encoding type. The encoding type with the
	 * highest probability is returned.
	 */

	public EncodingType detectEncoding(URL testurl)
	{
		byte[] rawtext = new byte[10000];
		int bytesread = 0, byteoffset = 0;
		EncodingType guess = EncodingType.OTHER;
		InputStream chinesestream;

		try
		{
			chinesestream = testurl.openStream();

			while ((bytesread = chinesestream.read(rawtext, byteoffset,
					rawtext.length - byteoffset)) > 0)
			{
				byteoffset += bytesread;
			}
			;
			chinesestream.close();
			guess = detectEncoding(rawtext);

		}
		catch (Exception e)
		{
			System.err.println("Error loading or using URL " + e.toString());
			guess = EncodingType.OTHER;
		}

		return guess;
	}

	/**
	 * Function : detectEncoding Aruguments: File Returns : One of the encodings
	 * from the Encoding enumeration (GB2312, HZ, BIG5, EUC_TW, ASCII, or OTHER)
	 * Description: This function looks at the file and assigns it a probability
	 * score for each encoding type. The encoding type with the highest
	 * probability is returned.
	 */

	public EncodingType detectEncoding(File testfile)
	{
		FileInputStream chinesefile = null;
		byte[] rawtext;

		rawtext = new byte[(int) testfile.length()];
		try
		{
			chinesefile = new FileInputStream(testfile);
			chinesefile.read(rawtext);
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e);
		}finally{
			try {
				if(null != chinesefile)
					chinesefile.close();
			} catch (Exception e) {
				// 忽略掉该异常
			}
		}

		return detectEncoding(rawtext);
	}

	/**
	 * Function : detectEncoding Aruguments: byte array Returns : One of the
	 * encodings from the Encoding enumeration (GB2312, HZ, BIG5, EUC_TW, ASCII,
	 * or OTHER) Description: This function looks at the byte array and assigns
	 * it a probability score for each encoding type. The encoding type with the
	 * highest probability is returned.
	 */

	public EncodingType detectEncoding(byte[] rawtext)
	{
		int[] scores;
		int maxscore = 0;
		EncodingType encoding_guess = EncodingType.OTHER;

		scores = new int[EncodingType.values().length];

		// Assign Scores
		scores[EncodingType.GB2312.ordinal()] = gb2312_probability(rawtext);
		scores[EncodingType.GBK.ordinal()] = gbk_probability(rawtext);
		scores[EncodingType.HZ.ordinal()] = hz_probability(rawtext);
		scores[EncodingType.BIG5.ordinal()] = big5_probability(rawtext);
		scores[EncodingType.EUC_TW.ordinal()] = euc_tw_probability(rawtext);
		scores[EncodingType.ISO_2022_CN.ordinal()] = iso_2022_cn_probability(rawtext);
		scores[EncodingType.UTF8.ordinal()] = utf8_probability(rawtext);
		scores[EncodingType.UNICODE.ordinal()] = utf16_probability(rawtext);
		scores[EncodingType.ASCII.ordinal()] = ascii_probability(rawtext);
		scores[EncodingType.OTHER.ordinal()] = 0;

		for(EncodingType type : EncodingType.values()){
			if (scores[type.ordinal()] > maxscore)
			{
				encoding_guess = type;
				maxscore = scores[type.ordinal()];
			}
		}

		// Return OTHER if nothing scored above 50
		if (maxscore <= 50)
		{
			encoding_guess = EncodingType.OTHER;
		}

		return encoding_guess;
	}

	/*
	 * Function: gb2312_probability Argument: pointer to byte array Returns :
	 * number from 0 to 100 representing probability text in array uses GB-2312
	 * encoding
	 */

	int gb2312_probability(byte[] rawtext)
	{
		int i, rawtextlen = 0;

		int dbchars = 1, gbchars = 1;
		long gbfreq = 0, totalfreq = 1;
		float rangeval = 0, freqval = 0;
		int row, column;

		// Stage 1: Check to see if characters fit into acceptable ranges

		rawtextlen = rawtext.length;
		for (i = 0; i < rawtextlen - 1; i++)
		{
			// System.err.println(rawtext[i]);
			if (rawtext[i] >= 0)
			{
				// asciichars++;
			}
			else
			{
				dbchars++;
				if ((byte) 0xA1 <= rawtext[i] && rawtext[i] <= (byte) 0xF7
						&& (byte) 0xA1 <= rawtext[i + 1]
						&& rawtext[i + 1] <= (byte) 0xFE)
				{
					gbchars++;
					totalfreq += 500;
					row = rawtext[i] + 256 - 0xA1;
					column = rawtext[i + 1] + 256 - 0xA1;
					if (GBFreq[row][column] != 0)
					{
						gbfreq += GBFreq[row][column];
					}
					else if (15 <= row && row < 55)
					{
						gbfreq += 200;
					}

				}
				i++;
			}
		}
		rangeval = 50 * ((float) gbchars / (float) dbchars);
		freqval = 50 * ((float) gbfreq / (float) totalfreq);

		return (int) (rangeval + freqval);
	}

	/*
	 * Function: gb2312_probability Argument: pointer to byte array Returns :
	 * number from 0 to 100 representing probability text in array uses GB-2312
	 * encoding
	 */

	int gbk_probability(byte[] rawtext)
	{
		int i, rawtextlen = 0;

		int dbchars = 1, gbchars = 1;
		long gbfreq = 0, totalfreq = 1;
		float rangeval = 0, freqval = 0;
		int row, column;

		// Stage 1: Check to see if characters fit into acceptable ranges
		rawtextlen = rawtext.length;
		for (i = 0; i < rawtextlen - 1; i++)
		{
			// System.err.println(rawtext[i]);
			if (rawtext[i] >= 0)
			{
				// asciichars++;
			}
			else
			{
				dbchars++;
				if ((byte) 0xA1 <= rawtext[i]
						&& rawtext[i] <= (byte) 0xF7
						&& // Original GB
						// range
						(byte) 0xA1 <= rawtext[i + 1]
						&& rawtext[i + 1] <= (byte) 0xFE)
				{
					gbchars++;
					totalfreq += 500;
					row = rawtext[i] + 256 - 0xA1;
					column = rawtext[i + 1] + 256 - 0xA1;

					// System.out.println("original row " + row + " column " +
					// column);
					if (GBFreq[row][column] != 0)
					{
						gbfreq += GBFreq[row][column];
					}
					else if (15 <= row && row < 55)
					{
						gbfreq += 200;
					}

				}
				else if ((byte) 0x81 <= rawtext[i] && rawtext[i] <= (byte) 0xFE
						&& // Extended
						// GB
						// range
						(((byte) 0x80 <= rawtext[i + 1] && rawtext[i + 1] <= (byte) 0xFE) || ((byte) 0x40 <= rawtext[i + 1] && rawtext[i + 1] <= (byte) 0x7E)))
				{
					gbchars++;
					totalfreq += 500;
					row = rawtext[i] + 256 - 0x81;
					if (0x40 <= rawtext[i + 1] && rawtext[i + 1] <= 0x7E)
					{
						column = rawtext[i + 1] - 0x40;
					}
					else
					{
						column = rawtext[i + 1] + 256 - 0x80;
					}
					// System.out.println("extended row " + row + " column " +
					// column + "
					// rawtext[i] " + rawtext[i]);
					if (GBKFreq[row][column] != 0)
					{
						gbfreq += GBKFreq[row][column];
					}
				}
				i++;
			}
		}
		rangeval = 50 * ((float) gbchars / (float) dbchars);
		freqval = 50 * ((float) gbfreq / (float) totalfreq);

		// For regular GB files, this would give the same score, so I handicap
		// it
		// slightly
		return (int) (rangeval + freqval) - 1;
	}

	/*
	 * Function: hz_probability Argument: byte array Returns : number from 0 to
	 * 100 representing probability text in array uses HZ encoding
	 */

	int hz_probability(byte[] rawtext)
	{
		int i, rawtextlen;
		int hzchars = 0, dbchars = 1;
		long hzfreq = 0, totalfreq = 1;
		float rangeval = 0, freqval = 0;
		int hzstart = 0, hzend = 0;
		int row, column;

		rawtext = Arrays.copyOf(rawtext, rawtext.length + 1);
		
		rawtextlen = rawtext.length;

		for (i = 0; i < rawtextlen; i++)
		{
			if (rawtext[i] == '~')
			{
				if (rawtext[i + 1] == '{')
				{
					hzstart++;
					i += 2;
					while (i < rawtextlen - 1)
					{
						if (rawtext[i] == 0x0A || rawtext[i] == 0x0D)
						{
							break;
						}
						else if (rawtext[i] == '~' && rawtext[i + 1] == '}')
						{
							hzend++;
							i++;
							break;
						}
						else if ((0x21 <= rawtext[i] && rawtext[i] <= 0x77)
								&& (0x21 <= rawtext[i + 1] && rawtext[i + 1] <= 0x77))
						{
							hzchars += 2;
							row = rawtext[i] - 0x21;
							column = rawtext[i + 1] - 0x21;
							totalfreq += 500;
							if (GBFreq[row][column] != 0)
							{
								hzfreq += GBFreq[row][column];
							}
							else if (15 <= row && row < 55)
							{
								hzfreq += 200;
							}
						}
						else if ((0xA1 <= rawtext[i] && rawtext[i] <= 0xF7)
								&& (0xA1 <= rawtext[i + 1] && rawtext[i + 1] <= 0xF7))
						{
							hzchars += 2;
							row = rawtext[i] + 256 - 0xA1;
							column = rawtext[i + 1] + 256 - 0xA1;
							totalfreq += 500;
							if (GBFreq[row][column] != 0)
							{
								hzfreq += GBFreq[row][column];
							}
							else if (15 <= row && row < 55)
							{
								hzfreq += 200;
							}
						}
						dbchars += 2;
						i += 2;
					}
				}
				else if (rawtext[i + 1] == '}')
				{
					hzend++;
					i++;
				}
				else if (rawtext[i + 1] == '~')
				{
					i++;
				}
			}

		}

		if (hzstart > 4)
		{
			rangeval = 50;
		}
		else if (hzstart > 1)
		{
			rangeval = 41;
		}
		else if (hzstart > 0)
		{ // Only 39 in case the sequence happened to occur
			rangeval = 39; // in otherwise non-Hz text
		}
		else
		{
			rangeval = 0;
		}
		freqval = 50 * ((float) hzfreq / (float) totalfreq);

		return (int) (rangeval + freqval);
	}

	/**
	 * Function: big5_probability Argument: byte array Returns : number from 0
	 * to 100 representing probability text in array uses Big5 encoding
	 */

	int big5_probability(byte[] rawtext)
	{
		int i, rawtextlen = 0;
		int dbchars = 1, bfchars = 1;
		float rangeval = 0, freqval = 0;
		long bffreq = 0, totalfreq = 1;
		int row, column;

		// Check to see if characters fit into acceptable ranges

		rawtextlen = rawtext.length;
		for (i = 0; i < rawtextlen - 1; i++)
		{
			if (rawtext[i] >= 0)
			{
				// asciichars++;
			}
			else
			{
				dbchars++;
				if ((byte) 0xA1 <= rawtext[i]
						&& rawtext[i] <= (byte) 0xF9
						&& (((byte) 0x40 <= rawtext[i + 1] && rawtext[i + 1] <= (byte) 0x7E) || ((byte) 0xA1 <= rawtext[i + 1] && rawtext[i + 1] <= (byte) 0xFE)))
				{
					bfchars++;
					totalfreq += 500;
					row = rawtext[i] + 256 - 0xA1;
					if (0x40 <= rawtext[i + 1] && rawtext[i + 1] <= 0x7E)
					{
						column = rawtext[i + 1] - 0x40;
					}
					else
					{
						column = rawtext[i + 1] + 256 - 0x61;
					}
					if (Big5Freq[row][column] != 0)
					{
						bffreq += Big5Freq[row][column];
					}
					else if (3 <= row && row <= 37)
					{
						bffreq += 200;
					}
				}
				i++;
			}
		}
		rangeval = 50 * ((float) bfchars / (float) dbchars);
		freqval = 50 * ((float) bffreq / (float) totalfreq);

		return (int) (rangeval + freqval);
	}

	/*
	 * Function: euc_tw_probability Argument: byte array Returns : number from 0
	 * to 100 representing probability text in array uses EUC-TW (CNS 11643)
	 * encoding
	 */

	int euc_tw_probability(byte[] rawtext)
	{
		int i, rawtextlen = 0;
		int dbchars = 1, cnschars = 1;
		long cnsfreq = 0, totalfreq = 1;
		float rangeval = 0, freqval = 0;
		int row, column;

		// Check to see if characters fit into acceptable ranges
		// and have expected frequency of use

		rawtextlen = rawtext.length;
		for (i = 0; i < rawtextlen - 1; i++)
		{
			if (rawtext[i] >= 0)
			{ // in ASCII range
				// asciichars++;
			}
			else
			{ // high bit set
				dbchars++;
				if (i + 3 < rawtextlen && (byte) 0x8E == rawtext[i]
						&& (byte) 0xA1 <= rawtext[i + 1]
						&& rawtext[i + 1] <= (byte) 0xB0
						&& (byte) 0xA1 <= rawtext[i + 2]
						&& rawtext[i + 2] <= (byte) 0xFE
						&& (byte) 0xA1 <= rawtext[i + 3]
						&& rawtext[i + 3] <= (byte) 0xFE)
				{ // Planes 1
					// - 16

					cnschars++;
					// System.out.println("plane 2 or above CNS char");
					// These are all less frequent chars so just ignore freq
					i += 3;
				}
				else if ((byte) 0xA1 <= rawtext[i]
						&& rawtext[i] <= (byte) 0xFE
						&& // Plane
						// 1
						(byte) 0xA1 <= rawtext[i + 1]
						&& rawtext[i + 1] <= (byte) 0xFE)
				{
					cnschars++;
					totalfreq += 500;
					row = rawtext[i] + 256 - 0xA1;
					column = rawtext[i + 1] + 256 - 0xA1;
					if (EUC_TWFreq[row][column] != 0)
					{
						cnsfreq += EUC_TWFreq[row][column];
					}
					else if (35 <= row && row <= 92)
					{
						cnsfreq += 150;
					}
					i++;
				}
			}
		}

		rangeval = 50 * ((float) cnschars / (float) dbchars);
		freqval = 50 * ((float) cnsfreq / (float) totalfreq);

		return (int) (rangeval + freqval);
	}

	/*
	 * Function: iso_2022_cn_probability Argument: byte array Returns : number
	 * from 0 to 100 representing probability text in array uses ISO 2022-CN
	 * encoding WORKS FOR BASIC CASES, BUT STILL NEEDS MORE WORK
	 */

	int iso_2022_cn_probability(byte[] rawtext)
	{
		int i, rawtextlen = 0;
		int dbchars = 1, isochars = 1;
		long isofreq = 0, totalfreq = 1;
		float rangeval = 0, freqval = 0;
		int row, column;

		// Check to see if characters fit into acceptable ranges
		// and have expected frequency of use

		rawtextlen = rawtext.length;
		for (i = 0; i < rawtextlen - 1; i++)
		{
			if (rawtext[i] == (byte) 0x1B && i + 3 < rawtextlen)
			{ // Escape char ESC
				if (rawtext[i + 1] == (byte) 0x24 && rawtext[i + 2] == 0x29
						&& rawtext[i + 3] == (byte) 0x41)
				{ // GB Escape $ ) A
					i += 4;
					while (rawtext[i] != (byte) 0x1B)
					{
						dbchars++;
						if ((0x21 <= rawtext[i] && rawtext[i] <= 0x77)
								&& (0x21 <= rawtext[i + 1] && rawtext[i + 1] <= 0x77))
						{
							isochars++;
							row = rawtext[i] - 0x21;
							column = rawtext[i + 1] - 0x21;
							totalfreq += 500;
							if (GBFreq[row][column] != 0)
							{
								isofreq += GBFreq[row][column];
							}
							else if (15 <= row && row < 55)
							{
								isofreq += 200;
							}
							i++;
						}
						i++;
					}
				}
				else if (i + 3 < rawtextlen && rawtext[i + 1] == (byte) 0x24
						&& rawtext[i + 2] == (byte) 0x29
						&& rawtext[i + 3] == (byte) 0x47)
				{
					// CNS Escape $ ) G
					i += 4;
					while (rawtext[i] != (byte) 0x1B)
					{
						dbchars++;
						if ((byte) 0x21 <= rawtext[i]
								&& rawtext[i] <= (byte) 0x7E
								&& (byte) 0x21 <= rawtext[i + 1]
								&& rawtext[i + 1] <= (byte) 0x7E)
						{
							isochars++;
							totalfreq += 500;
							row = rawtext[i] - 0x21;
							column = rawtext[i + 1] - 0x21;
							if (EUC_TWFreq[row][column] != 0)
							{
								isofreq += EUC_TWFreq[row][column];
							}
							else if (35 <= row && row <= 92)
							{
								isofreq += 150;
							}
							i++;
						}
						i++;
					}
				}
				if (rawtext[i] == (byte) 0x1B && i + 2 < rawtextlen
						&& rawtext[i + 1] == (byte) 0x28
						&& rawtext[i + 2] == (byte) 0x42)
				{ // ASCII:
					// ESC ( B
					i += 2;
				}
			}
		}
		rangeval = 50 * ((float) isochars / (float) dbchars);
		freqval = 50 * ((float) isofreq / (float) totalfreq);

		// System.out.println("isochars dbchars isofreq totalfreq " + isochars +
		// " "
		// + dbchars + " " + isofreq + " " + totalfreq + " " + rangeval + " " +
		// freqval);

		return (int) (rangeval + freqval);
		// return 0;
	}

	/*
	 * Function: utf8_probability Argument: byte array Returns : number from 0
	 * to 100 representing probability text in array uses UTF-8 encoding of
	 * Unicode
	 */

	int utf8_probability(byte[] rawtext)
	{
		int score = 0;
		int i, rawtextlen = 0;
		int goodbytes = 0, asciibytes = 0;

		// Maybe also use UTF8 Byte Order Mark: EF BB BF

		// Check to see if characters fit into acceptable ranges
		rawtextlen = rawtext.length;
		for (i = 0; i < rawtextlen; i++)
		{
			if ((rawtext[i] & (byte) 0x7F) == rawtext[i])
			{ // One byte
				asciibytes++;
				// Ignore ASCII, can throw off count
			}
			else if (-64 <= rawtext[i] && rawtext[i] <= -33
					&& // Two bytes
					i + 1 < rawtextlen && -128 <= rawtext[i + 1]
					&& rawtext[i + 1] <= -65)
			{
				goodbytes += 2;
				i++;
			}
			else if (-32 <= rawtext[i]
					&& rawtext[i] <= -17
					&& // Three bytes
					i + 2 < rawtextlen && -128 <= rawtext[i + 1]
					&& rawtext[i + 1] <= -65 && -128 <= rawtext[i + 2]
					&& rawtext[i + 2] <= -65)
			{
				goodbytes += 3;
				i += 2;
			}
		}

		if (asciibytes == rawtextlen)
		{
			return 0;
		}

		score = (int) (100 * ((float) goodbytes / (float) (rawtextlen - asciibytes)));

		// If not above 98, reduce to zero to prevent coincidental matches
		// Allows for some (few) bad formed sequences
		if (score > 98)
		{
			return score;
		}
		else if (score > 95 && goodbytes > 30)
		{
			return score;
		}
		else
		{
			return 0;
		}

	}

	/*
	 * Function: utf16_probability Argument: byte array Returns : number from 0
	 * to 100 representing probability text in array uses UTF-16 encoding of
	 * Unicode, guess based on BOM // NOT VERY GENERAL, NEEDS MUCH MORE WORK
	 */

	int utf16_probability(byte[] rawtext)
	{
		// int score = 0;
		// int i, rawtextlen = 0;
		// int goodbytes = 0, asciibytes = 0;

		if (((byte) 0xFE == rawtext[0] && (byte) 0xFF == rawtext[1]) || // Big-endian
				((byte) 0xFF == rawtext[0] && (byte) 0xFE == rawtext[1]))
		{ // Little-endian
			return 100;
		}

		return 0;

		/*
		 * // Check to see if characters fit into acceptable ranges rawtextlen =
		 * rawtext.length; for (i = 0; i < rawtextlen; i++) { if ((rawtext[i] &
		 * (byte)0x7F) == rawtext[i]) { // One byte goodbytes += 1;
		 * asciibytes++; } else if ((rawtext[i] & (byte)0xDF) == rawtext[i]) { //
		 * Two bytes if (i+1 < rawtextlen && (rawtext[i+1] & (byte)0xBF) ==
		 * rawtext[i+1]) { goodbytes += 2; i++; } } else if ((rawtext[i] &
		 * (byte)0xEF) == rawtext[i]) { // Three bytes if (i+2 < rawtextlen &&
		 * (rawtext[i+1] & (byte)0xBF) == rawtext[i+1] && (rawtext[i+2] &
		 * (byte)0xBF) == rawtext[i+2]) { goodbytes += 3; i+=2; } } } score =
		 * (int)(100 * ((float)goodbytes/(float)rawtext.length)); // An all
		 * ASCII file is also a good UTF8 file, but I'd rather it // get
		 * identified as ASCII. Can delete following 3 lines otherwise if
		 * (goodbytes == asciibytes) { score = 0; } // If not above 90, reduce
		 * to zero to prevent coincidental matches if (score > 90) { return
		 * score; } else { return 0; }
		 */

	}

	/*
	 * Function: ascii_probability Argument: byte array Returns : number from 0
	 * to 100 representing probability text in array uses all ASCII Description:
	 * Sees if array has any characters not in ASCII range, if so, score is
	 * reduced
	 */

	int ascii_probability(byte[] rawtext)
	{
		int score = 70;
		int i, rawtextlen;

		rawtextlen = rawtext.length;

		for (i = 0; i < rawtextlen; i++)
		{
			if (rawtext[i] < 0)
			{
				score = score - 5;
			}
			else if (rawtext[i] == (byte) 0x1B)
			{ // ESC (used by ISO 2022)
				score = score - 5;
			}
		}

		return score;
	}

	void initialize_frequencies()
	{
		int i, j;

		for (i = 0; i < 93; i++)
		{
			for (j = 0; j < 93; j++)
			{
				GBFreq[i][j] = 0;
			}
		}

		for (i = 0; i < 126; i++)
		{
			for (j = 0; j < 191; j++)
			{
				GBKFreq[i][j] = 0;
			}
		}

		for (i = 0; i < 93; i++)
		{
			for (j = 0; j < 157; j++)
			{
				Big5Freq[i][j] = 0;
			}
		}

		for (i = 0; i < 93; i++)
		{
			for (j = 0; j < 93; j++)
			{
				EUC_TWFreq[i][j] = 0;
			}
		}

		GBFreq[20][35] = 599;
		GBFreq[49][26] = 598;
		GBFreq[41][38] = 597;
		GBFreq[17][26] = 596;
		GBFreq[32][42] = 595;
		GBFreq[39][42] = 594;
		GBFreq[45][49] = 593;
		GBFreq[51][57] = 592;
		GBFreq[50][47] = 591;
		GBFreq[42][90] = 590;
		GBFreq[52][65] = 589;
		GBFreq[53][47] = 588;
		GBFreq[19][82] = 587;
		GBFreq[31][19] = 586;
		GBFreq[40][46] = 585;
		GBFreq[24][89] = 584;
		GBFreq[23][85] = 583;
		GBFreq[20][28] = 582;
		GBFreq[42][20] = 581;
		GBFreq[34][38] = 580;
		GBFreq[45][9] = 579;
		GBFreq[54][50] = 578;
		GBFreq[25][44] = 577;
		GBFreq[35][66] = 576;
		GBFreq[20][55] = 575;
		GBFreq[18][85] = 574;
		GBFreq[20][31] = 573;
		GBFreq[49][17] = 572;
		GBFreq[41][16] = 571;
		GBFreq[35][73] = 570;
		GBFreq[20][34] = 569;
		GBFreq[29][44] = 568;
		GBFreq[35][38] = 567;
		GBFreq[49][9] = 566;
		GBFreq[46][33] = 565;
		GBFreq[49][51] = 564;
		GBFreq[40][89] = 563;
		GBFreq[26][64] = 562;
		GBFreq[54][51] = 561;
		GBFreq[54][36] = 560;
		GBFreq[39][4] = 559;
		GBFreq[53][13] = 558;
		GBFreq[24][92] = 557;
		GBFreq[27][49] = 556;
		GBFreq[48][6] = 555;
		GBFreq[21][51] = 554;
		GBFreq[30][40] = 553;
		GBFreq[42][92] = 552;
		GBFreq[31][78] = 551;
		GBFreq[25][82] = 550;
		GBFreq[47][0] = 549;
		GBFreq[34][19] = 548;
		GBFreq[47][35] = 547;
		GBFreq[21][63] = 546;
		GBFreq[43][75] = 545;
		GBFreq[21][87] = 544;
		GBFreq[35][59] = 543;
		GBFreq[25][34] = 542;
		GBFreq[21][27] = 541;
		GBFreq[39][26] = 540;
		GBFreq[34][26] = 539;
		GBFreq[39][52] = 538;
		GBFreq[50][57] = 537;
		GBFreq[37][79] = 536;
		GBFreq[26][24] = 535;
		GBFreq[22][1] = 534;
		GBFreq[18][40] = 533;
		GBFreq[41][33] = 532;
		GBFreq[53][26] = 531;
		GBFreq[54][86] = 530;
		GBFreq[20][16] = 529;
		GBFreq[46][74] = 528;
		GBFreq[30][19] = 527;
		GBFreq[45][35] = 526;
		GBFreq[45][61] = 525;
		GBFreq[30][9] = 524;
		GBFreq[41][53] = 523;
		GBFreq[41][13] = 522;
		GBFreq[50][34] = 521;
		GBFreq[53][86] = 520;
		GBFreq[47][47] = 519;
		GBFreq[22][28] = 518;
		GBFreq[50][53] = 517;
		GBFreq[39][70] = 516;
		GBFreq[38][15] = 515;
		GBFreq[42][88] = 514;
		GBFreq[16][29] = 513;
		GBFreq[27][90] = 512;
		GBFreq[29][12] = 511;
		GBFreq[44][22] = 510;
		GBFreq[34][69] = 509;
		GBFreq[24][10] = 508;
		GBFreq[44][11] = 507;
		GBFreq[39][92] = 506;
		GBFreq[49][48] = 505;
		GBFreq[31][46] = 504;
		GBFreq[19][50] = 503;
		GBFreq[21][14] = 502;
		GBFreq[32][28] = 501;
		GBFreq[18][3] = 500;
		GBFreq[53][9] = 499;
		GBFreq[34][80] = 498;
		GBFreq[48][88] = 497;
		GBFreq[46][53] = 496;
		GBFreq[22][53] = 495;
		GBFreq[28][10] = 494;
		GBFreq[44][65] = 493;
		GBFreq[20][10] = 492;
		GBFreq[40][76] = 491;
		GBFreq[47][8] = 490;
		GBFreq[50][74] = 489;
		GBFreq[23][62] = 488;
		GBFreq[49][65] = 487;
		GBFreq[28][87] = 486;
		GBFreq[15][48] = 485;
		GBFreq[22][7] = 484;
		GBFreq[19][42] = 483;
		GBFreq[41][20] = 482;
		GBFreq[26][55] = 481;
		GBFreq[21][93] = 480;
		GBFreq[31][76] = 479;
		GBFreq[34][31] = 478;
		GBFreq[20][66] = 477;
		GBFreq[51][33] = 476;
		GBFreq[34][86] = 475;
		GBFreq[37][67] = 474;
		GBFreq[53][53] = 473;
		GBFreq[40][88] = 472;
		GBFreq[39][10] = 471;
		GBFreq[24][3] = 470;
		GBFreq[27][25] = 469;
		GBFreq[26][15] = 468;
		GBFreq[21][88] = 467;
		GBFreq[52][62] = 466;
		GBFreq[46][81] = 465;
		GBFreq[38][72] = 464;
		GBFreq[17][30] = 463;
		GBFreq[52][92] = 462;
		GBFreq[34][90] = 461;
		GBFreq[21][7] = 460;
		GBFreq[36][13] = 459;
		GBFreq[45][41] = 458;
		GBFreq[32][5] = 457;
		GBFreq[26][89] = 456;
		GBFreq[23][87] = 455;
		GBFreq[20][39] = 454;
		GBFreq[27][23] = 453;
		GBFreq[25][59] = 452;
		GBFreq[49][20] = 451;
		GBFreq[54][77] = 450;
		GBFreq[27][67] = 449;
		GBFreq[47][33] = 448;
		GBFreq[41][17] = 447;
		GBFreq[19][81] = 446;
		GBFreq[16][66] = 445;
		GBFreq[45][26] = 444;
		GBFreq[49][81] = 443;
		GBFreq[53][55] = 442;
		GBFreq[16][26] = 441;
		GBFreq[54][62] = 440;
		GBFreq[20][70] = 439;
		GBFreq[42][35] = 438;
		GBFreq[20][57] = 437;
		GBFreq[34][36] = 436;
		GBFreq[46][63] = 435;
		GBFreq[19][45] = 434;
		GBFreq[21][10] = 433;
		GBFreq[52][93] = 432;
		GBFreq[25][2] = 431;
		GBFreq[30][57] = 430;
		GBFreq[41][24] = 429;
		GBFreq[28][43] = 428;
		GBFreq[45][86] = 427;
		GBFreq[51][56] = 426;
		GBFreq[37][28] = 425;
		GBFreq[52][69] = 424;
		GBFreq[43][92] = 423;
		GBFreq[41][31] = 422;
		GBFreq[37][87] = 421;
		GBFreq[47][36] = 420;
		GBFreq[16][16] = 419;
		GBFreq[40][56] = 418;
		GBFreq[24][55] = 417;
		GBFreq[17][1] = 416;
		GBFreq[35][57] = 415;
		GBFreq[27][50] = 414;
		GBFreq[26][14] = 413;
		GBFreq[50][40] = 412;
		GBFreq[39][19] = 411;
		GBFreq[19][89] = 410;
		GBFreq[29][91] = 409;
		GBFreq[17][89] = 408;
		GBFreq[39][74] = 407;
		GBFreq[46][39] = 406;
		GBFreq[40][28] = 405;
		GBFreq[45][68] = 404;
		GBFreq[43][10] = 403;
		GBFreq[42][13] = 402;
		GBFreq[44][81] = 401;
		GBFreq[41][47] = 400;
		GBFreq[48][58] = 399;
		GBFreq[43][68] = 398;
		GBFreq[16][79] = 397;
		GBFreq[19][5] = 396;
		GBFreq[54][59] = 395;
		GBFreq[17][36] = 394;
		GBFreq[18][0] = 393;
		GBFreq[41][5] = 392;
		GBFreq[41][72] = 391;
		GBFreq[16][39] = 390;
		GBFreq[54][0] = 389;
		GBFreq[51][16] = 388;
		GBFreq[29][36] = 387;
		GBFreq[47][5] = 386;
		GBFreq[47][51] = 385;
		GBFreq[44][7] = 384;
		GBFreq[35][30] = 383;
		GBFreq[26][9] = 382;
		GBFreq[16][7] = 381;
		GBFreq[32][1] = 380;
		GBFreq[33][76] = 379;
		GBFreq[34][91] = 378;
		GBFreq[52][36] = 377;
		GBFreq[26][77] = 376;
		GBFreq[35][48] = 375;
		GBFreq[40][80] = 374;
		GBFreq[41][92] = 373;
		GBFreq[27][93] = 372;
		GBFreq[15][17] = 371;
		GBFreq[16][76] = 370;
		GBFreq[51][12] = 369;
		GBFreq[18][20] = 368;
		GBFreq[15][54] = 367;
		GBFreq[50][5] = 366;
		GBFreq[33][22] = 365;
		GBFreq[37][57] = 364;
		GBFreq[28][47] = 363;
		GBFreq[42][31] = 362;
		GBFreq[18][2] = 361;
		GBFreq[43][64] = 360;
		GBFreq[23][47] = 359;
		GBFreq[28][79] = 358;
		GBFreq[25][45] = 357;
		GBFreq[23][91] = 356;
		GBFreq[22][19] = 355;
		GBFreq[25][46] = 354;
		GBFreq[22][36] = 353;
		GBFreq[54][85] = 352;
		GBFreq[46][20] = 351;
		GBFreq[27][37] = 350;
		GBFreq[26][81] = 349;
		GBFreq[42][29] = 348;
		GBFreq[31][90] = 347;
		GBFreq[41][59] = 346;
		GBFreq[24][65] = 345;
		GBFreq[44][84] = 344;
		GBFreq[24][90] = 343;
		GBFreq[38][54] = 342;
		GBFreq[28][70] = 341;
		GBFreq[27][15] = 340;
		GBFreq[28][80] = 339;
		GBFreq[29][8] = 338;
		GBFreq[45][80] = 337;
		GBFreq[53][37] = 336;
		GBFreq[28][65] = 335;
		GBFreq[23][86] = 334;
		GBFreq[39][45] = 333;
		GBFreq[53][32] = 332;
		GBFreq[38][68] = 331;
		GBFreq[45][78] = 330;
		GBFreq[43][7] = 329;
		GBFreq[46][82] = 328;
		GBFreq[27][38] = 327;
		GBFreq[16][62] = 326;
		GBFreq[24][17] = 325;
		GBFreq[22][70] = 324;
		GBFreq[52][28] = 323;
		GBFreq[23][40] = 322;
		GBFreq[28][50] = 321;
		GBFreq[42][91] = 320;
		GBFreq[47][76] = 319;
		GBFreq[15][42] = 318;
		GBFreq[43][55] = 317;
		GBFreq[29][84] = 316;
		GBFreq[44][90] = 315;
		GBFreq[53][16] = 314;
		GBFreq[22][93] = 313;
		GBFreq[34][10] = 312;
		GBFreq[32][53] = 311;
		GBFreq[43][65] = 310;
		GBFreq[28][7] = 309;
		GBFreq[35][46] = 308;
		GBFreq[21][39] = 307;
		GBFreq[44][18] = 306;
		GBFreq[40][10] = 305;
		GBFreq[54][53] = 304;
		GBFreq[38][74] = 303;
		GBFreq[28][26] = 302;
		GBFreq[15][13] = 301;
		GBFreq[39][34] = 300;
		GBFreq[39][46] = 299;
		GBFreq[42][66] = 298;
		GBFreq[33][58] = 297;
		GBFreq[15][56] = 296;
		GBFreq[18][51] = 295;
		GBFreq[49][68] = 294;
		GBFreq[30][37] = 293;
		GBFreq[51][84] = 292;
		GBFreq[51][9] = 291;
		GBFreq[40][70] = 290;
		GBFreq[41][84] = 289;
		GBFreq[28][64] = 288;
		GBFreq[32][88] = 287;
		GBFreq[24][5] = 286;
		GBFreq[53][23] = 285;
		GBFreq[42][27] = 284;
		GBFreq[22][38] = 283;
		GBFreq[32][86] = 282;
		GBFreq[34][30] = 281;
		GBFreq[38][63] = 280;
		GBFreq[24][59] = 279;
		GBFreq[22][81] = 278;
		GBFreq[32][11] = 277;
		GBFreq[51][21] = 276;
		GBFreq[54][41] = 275;
		GBFreq[21][50] = 274;
		GBFreq[23][89] = 273;
		GBFreq[19][87] = 272;
		GBFreq[26][7] = 271;
		GBFreq[30][75] = 270;
		GBFreq[43][84] = 269;
		GBFreq[51][25] = 268;
		GBFreq[16][67] = 267;
		GBFreq[32][9] = 266;
		GBFreq[48][51] = 265;
		GBFreq[39][7] = 264;
		GBFreq[44][88] = 263;
		GBFreq[52][24] = 262;
		GBFreq[23][34] = 261;
		GBFreq[32][75] = 260;
		GBFreq[19][10] = 259;
		GBFreq[28][91] = 258;
		GBFreq[32][83] = 257;
		GBFreq[25][75] = 256;
		GBFreq[53][45] = 255;
		GBFreq[29][85] = 254;
		GBFreq[53][59] = 253;
		GBFreq[16][2] = 252;
		GBFreq[19][78] = 251;
		GBFreq[15][75] = 250;
		GBFreq[51][42] = 249;
		GBFreq[45][67] = 248;
		GBFreq[15][74] = 247;
		GBFreq[25][81] = 246;
		GBFreq[37][62] = 245;
		GBFreq[16][55] = 244;
		GBFreq[18][38] = 243;
		GBFreq[23][23] = 242;
	}
}
