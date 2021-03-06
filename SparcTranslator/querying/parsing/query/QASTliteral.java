/* Generated By:JJTree: Do not edit this line. QASTliteral.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=QAST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package querying.parsing.query;
import querying.parsing.query.ParseException;
public
class QASTliteral extends SimpleNode {  
  public boolean negated;
  public QASTliteral(int id) {
    super(id);
  }

  public QASTliteral(QueryParser p, int id) {
    super(p, id);
  }
  
	public void evaluateAllArithmetics() throws ParseException {
		evaluateAllArithmetics(this);
	}

	private void evaluateAllArithmetics(SimpleNode n) throws ParseException {
		if (n.id == QueryParserTreeConstants.JJTTERM) {
			SimpleNode child = (SimpleNode) n.jjtGetChild(0);
			if (child.id == QueryParserTreeConstants.JJTARITHMETICTERM) {
				TermEvaluator tv = new TermEvaluator((QASTarithmeticTerm) child);
				if (tv.isEvaluable()) {
					long value = tv.evaluate();
					if(value<0) {
						throw new ParseException("query contains an arithmetic term \""+n.toString()+"\" which evaluates to a negative number "+Long.toString(value));
					}
					QASTarithmeticTerm aterm = new QASTarithmeticTerm(
							QueryParserTreeConstants.JJTARITHMETICTERM, value);
					n.children[0] = aterm;
				}
			}
		}
		for(int i=0;i<n.jjtGetNumChildren();i++) {
			evaluateAllArithmetics((SimpleNode)n.jjtGetChild(i));
		}
	}
	
	public boolean isGround() {

		return isGround(this);
	}

	private boolean isGround(SimpleNode n) {
		if (n.id == QueryParserTreeConstants.JJTVAR) {
			return false;
		}

		boolean ground = true;
		for (int i = 0; i < n.jjtGetNumChildren(); i++) {
			if (!isGround((SimpleNode) n.jjtGetChild(i))) {
				ground = false;
				break;
			}
		}
		return ground;

	}
	
	public String toString()
	{
		return (negated?"-":"")+((QASTatom)this.jjtGetChild(0)).toString();
	}
	
	
	
}
/* JavaCC - OriginalChecksum=49797a1ca2918414ecd1d78e12465ec7 (do not edit this line) */
