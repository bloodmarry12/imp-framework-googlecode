package com.huawei.imp.framework.utils;

/**
 * 字符串工具类
 * @author ahli
 * @version IMPV100R001DA0, Oct 29, 2009 
 * @since CMS IMPV100R001DA0
 */
public class StringUtil
{
    
    public static String subStringByChar(String src, int startIndex){
    	final int SRC_LENGTH = getStringLength(src);
    	if(SRC_LENGTH < src.length() ){
    		return src;
    	}
    	int sum = 0;
    	StringBuffer sb = new StringBuffer(1000);
    	for(int i = 0; i < src.length(); i ++){
    		char c = src.charAt(i);
    		if (c > 256)
            {
                sum += 2;
            }
            else
            {
                sum += 1;
            }
    		if(sum > startIndex){
   				sb.append(c);
    		}
    	}
    	return sb.toString();
    }
    
    /**
     * 按照字符(一个中文两个字符，一个英文一个字符的方式截获)
     * @param src
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static String subStringByChar(String src, int startIndex, int endIndex){
    	final int SRC_LENGTH = getStringLength(src);
    	if(startIndex > SRC_LENGTH){
    		return "";
    	}
    	StringBuffer sb = new StringBuffer(1000);
    	int sum = 0 ;
    	for(int i = 0; i < src.length(); i ++){
    		char c = src.charAt(i);
    		if( sum >= endIndex){
    			break;
    		}
    		if (c > 256)
            {
                sum += 2;
            }
            else
            {
                sum += 1;
            }
    		if(sum > startIndex && sum <= endIndex){
   				sb.append(c);
    		}
    	}
    	return sb.toString();
    }
    
    /**
     * 获取字符串的长度
     * @param aStr
     * @return int
     */
    public static int getStringLength(String aStr)
    {
        int len = aStr.length();
        int result = 0;
        char c;
        for (int i = 0; i < len; i++)
        {
            c = aStr.charAt(i);
            if (c > 256)
            {
                result += 2;
            }
            else
            {
                result += 1;
            }
        }
        return result;
    }
    
//    public static void main(String[] args){
//    	String str = "判一审判决认定被告a人王志华犯劫持航空器罪判处有期徒刑十二年剥夺政治权阿德";
//    	System.out.println(StringUtil.getShortStr(str, 64));
//    	System.out.println(StringUtil.subStringByChar(str, 0, 6));
//    }
    
    //判断字符集
	public static int utf8_probability(byte[] rawtext) {
		int score = 0;
		int i, rawtextlen = 0;
		int goodbytes = 0, asciibytes = 0;

		// Maybe also use UTF8 Byte Order Mark: EF BB BF

		// Check to see if characters fit into acceptable ranges
		rawtextlen = rawtext.length;
		for (i = 0; i < rawtextlen; i++) {
			if ((rawtext[i] & (byte) 0x7F) == rawtext[i]) { // One byte
				asciibytes++;
				// Ignore ASCII, can throw off count
			} else if (-64 <= rawtext[i] && rawtext[i] <= -33
					&& // Two bytes
					i + 1 < rawtextlen && -128 <= rawtext[i + 1]
					&& rawtext[i + 1] <= -65) {
				goodbytes += 2;
				i++;
			} else if (-32 <= rawtext[i]
					&& rawtext[i] <= -17
					&& // Three bytes
					i + 2 < rawtextlen && -128 <= rawtext[i + 1]
					&& rawtext[i + 1] <= -65 && -128 <= rawtext[i + 2]
					&& rawtext[i + 2] <= -65) {
				goodbytes += 3;
				i += 2;
			}
		}

		if (asciibytes == rawtextlen) {
			return 0;
		}

		score = (int) (100 * ((float) goodbytes / (float) (rawtextlen - asciibytes)));

		// If not above 98, reduce to zero to prevent coincidental matches
		// Allows for some (few) bad formed sequences
		if (score > 98) {
			return score;
		} else if (score > 95 && goodbytes > 30) {
			return score;
		} else {
			return 0;
		}
	}
}