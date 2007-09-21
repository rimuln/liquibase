package liquibase.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class StringUtilsTest {
    
    @Test
    public void noComments() {
        String noComments=" Some text but no comments";
        String result = StringUtils.stripComments(noComments);
        assertEquals(noComments,result);
    }
    
    @Test
    public void singleLineNoNewLine() {
        String sql = "Some text" ;
        String comment = " -- with comment";
        String totalLine=sql + comment ;
        String result = StringUtils.stripComments(totalLine);
        
        assertEquals(sql,result);
    }
    
    @Test
    public void singleLineNoFollowOnLine() {
        String sql = "Some text" ;
        String comment = " -- with comment\n";
        String totalLine=sql + comment ;
        String result = StringUtils.stripComments(totalLine);
        
        assertEquals(sql+"\n",result);
    }
    
    @Test
    public void singleLineMultipleComments() {
        String sql = "Some text" ;
        String comment = " -- with comment";
        String totalLine=sql + comment + "\n"+ sql + comment ;
        String result = StringUtils.stripComments(totalLine);
        
        assertEquals(sql+"\n"+sql,result);
    }
    
    @Test
    public void singleLineWithFollowupLine() {
        String sql = "Some text" ;
        String comment = " -- with comment";
        String totalLine=sql + comment + "\n" + sql ;
        String result = StringUtils.stripComments(totalLine);
        
        assertEquals(sql + "\n" + sql,result);
    }
    
    @Test
    public void multiLineOnOwnLine() {
        String sql = "/*Some text\nmore text*/" ;
        
        String result = StringUtils.stripComments(sql);
        assertEquals("\n",result);
    }
    
    @Test
    public void multiLineAfterSQL() {
        String sql = "some sql";
        String comment = "/*Some text\nmore text*/" ;
        String total = sql + comment;
        String result = StringUtils.stripComments(total);
        assertEquals(sql+"\n",result);
    }
    
    @Test
    public void multiLineFinishesWithTextOnLine() {
        String sql = "some sql";
        String comment = "/*Some text\nmore text*/" ;
        String total = comment + sql;
        String result = StringUtils.stripComments(total);
        assertEquals("\n"+sql,result);
    }
    
    @Test
    public void multiLineStartAndFinishWithSQL() {
        String sql = "some sql";
        String comment = "/*Some text\nmore text*/" ;
        String total = sql + comment + sql;
        String result = StringUtils.stripComments(total);
        assertEquals(sql + "\n" + sql,result);
    }
}
