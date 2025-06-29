package com.notepadxx.antlr.golang;
// Generated from golang/GoParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GoParser}.
 */
public interface GoParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GoParser#sourceFile}.
	 * @param ctx the parse tree
	 */
	void enterSourceFile(GoParser.SourceFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#sourceFile}.
	 * @param ctx the parse tree
	 */
	void exitSourceFile(GoParser.SourceFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#packageClause}.
	 * @param ctx the parse tree
	 */
	void enterPackageClause(GoParser.PackageClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#packageClause}.
	 * @param ctx the parse tree
	 */
	void exitPackageClause(GoParser.PackageClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#importDecl}.
	 * @param ctx the parse tree
	 */
	void enterImportDecl(GoParser.ImportDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#importDecl}.
	 * @param ctx the parse tree
	 */
	void exitImportDecl(GoParser.ImportDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#importSpec}.
	 * @param ctx the parse tree
	 */
	void enterImportSpec(GoParser.ImportSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#importSpec}.
	 * @param ctx the parse tree
	 */
	void exitImportSpec(GoParser.ImportSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#importPath}.
	 * @param ctx the parse tree
	 */
	void enterImportPath(GoParser.ImportPathContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#importPath}.
	 * @param ctx the parse tree
	 */
	void exitImportPath(GoParser.ImportPathContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(GoParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(GoParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#constDecl}.
	 * @param ctx the parse tree
	 */
	void enterConstDecl(GoParser.ConstDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#constDecl}.
	 * @param ctx the parse tree
	 */
	void exitConstDecl(GoParser.ConstDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#constSpec}.
	 * @param ctx the parse tree
	 */
	void enterConstSpec(GoParser.ConstSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#constSpec}.
	 * @param ctx the parse tree
	 */
	void exitConstSpec(GoParser.ConstSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierList(GoParser.IdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierList(GoParser.IdentifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(GoParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(GoParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeDecl}.
	 * @param ctx the parse tree
	 */
	void enterTypeDecl(GoParser.TypeDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeDecl}.
	 * @param ctx the parse tree
	 */
	void exitTypeDecl(GoParser.TypeDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeSpec}.
	 * @param ctx the parse tree
	 */
	void enterTypeSpec(GoParser.TypeSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeSpec}.
	 * @param ctx the parse tree
	 */
	void exitTypeSpec(GoParser.TypeSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#aliasDecl}.
	 * @param ctx the parse tree
	 */
	void enterAliasDecl(GoParser.AliasDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#aliasDecl}.
	 * @param ctx the parse tree
	 */
	void exitAliasDecl(GoParser.AliasDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeDef}.
	 * @param ctx the parse tree
	 */
	void enterTypeDef(GoParser.TypeDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeDef}.
	 * @param ctx the parse tree
	 */
	void exitTypeDef(GoParser.TypeDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameters(GoParser.TypeParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameters(GoParser.TypeParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeParameterDecl}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameterDecl(GoParser.TypeParameterDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeParameterDecl}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameterDecl(GoParser.TypeParameterDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeElement}.
	 * @param ctx the parse tree
	 */
	void enterTypeElement(GoParser.TypeElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeElement}.
	 * @param ctx the parse tree
	 */
	void exitTypeElement(GoParser.TypeElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeTerm}.
	 * @param ctx the parse tree
	 */
	void enterTypeTerm(GoParser.TypeTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeTerm}.
	 * @param ctx the parse tree
	 */
	void exitTypeTerm(GoParser.TypeTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDecl(GoParser.FunctionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDecl(GoParser.FunctionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#methodDecl}.
	 * @param ctx the parse tree
	 */
	void enterMethodDecl(GoParser.MethodDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#methodDecl}.
	 * @param ctx the parse tree
	 */
	void exitMethodDecl(GoParser.MethodDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#receiver}.
	 * @param ctx the parse tree
	 */
	void enterReceiver(GoParser.ReceiverContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#receiver}.
	 * @param ctx the parse tree
	 */
	void exitReceiver(GoParser.ReceiverContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(GoParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(GoParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#varSpec}.
	 * @param ctx the parse tree
	 */
	void enterVarSpec(GoParser.VarSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#varSpec}.
	 * @param ctx the parse tree
	 */
	void exitVarSpec(GoParser.VarSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(GoParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(GoParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#statementList}.
	 * @param ctx the parse tree
	 */
	void enterStatementList(GoParser.StatementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#statementList}.
	 * @param ctx the parse tree
	 */
	void exitStatementList(GoParser.StatementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(GoParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(GoParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#simpleStmt}.
	 * @param ctx the parse tree
	 */
	void enterSimpleStmt(GoParser.SimpleStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#simpleStmt}.
	 * @param ctx the parse tree
	 */
	void exitSimpleStmt(GoParser.SimpleStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#expressionStmt}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStmt(GoParser.ExpressionStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#expressionStmt}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStmt(GoParser.ExpressionStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#sendStmt}.
	 * @param ctx the parse tree
	 */
	void enterSendStmt(GoParser.SendStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#sendStmt}.
	 * @param ctx the parse tree
	 */
	void exitSendStmt(GoParser.SendStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#incDecStmt}.
	 * @param ctx the parse tree
	 */
	void enterIncDecStmt(GoParser.IncDecStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#incDecStmt}.
	 * @param ctx the parse tree
	 */
	void exitIncDecStmt(GoParser.IncDecStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(GoParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(GoParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#assign_op}.
	 * @param ctx the parse tree
	 */
	void enterAssign_op(GoParser.Assign_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#assign_op}.
	 * @param ctx the parse tree
	 */
	void exitAssign_op(GoParser.Assign_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#shortVarDecl}.
	 * @param ctx the parse tree
	 */
	void enterShortVarDecl(GoParser.ShortVarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#shortVarDecl}.
	 * @param ctx the parse tree
	 */
	void exitShortVarDecl(GoParser.ShortVarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#labeledStmt}.
	 * @param ctx the parse tree
	 */
	void enterLabeledStmt(GoParser.LabeledStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#labeledStmt}.
	 * @param ctx the parse tree
	 */
	void exitLabeledStmt(GoParser.LabeledStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(GoParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(GoParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(GoParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(GoParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#continueStmt}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(GoParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#continueStmt}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(GoParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#gotoStmt}.
	 * @param ctx the parse tree
	 */
	void enterGotoStmt(GoParser.GotoStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#gotoStmt}.
	 * @param ctx the parse tree
	 */
	void exitGotoStmt(GoParser.GotoStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#fallthroughStmt}.
	 * @param ctx the parse tree
	 */
	void enterFallthroughStmt(GoParser.FallthroughStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#fallthroughStmt}.
	 * @param ctx the parse tree
	 */
	void exitFallthroughStmt(GoParser.FallthroughStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#deferStmt}.
	 * @param ctx the parse tree
	 */
	void enterDeferStmt(GoParser.DeferStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#deferStmt}.
	 * @param ctx the parse tree
	 */
	void exitDeferStmt(GoParser.DeferStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(GoParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(GoParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#switchStmt}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStmt(GoParser.SwitchStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#switchStmt}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStmt(GoParser.SwitchStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#exprSwitchStmt}.
	 * @param ctx the parse tree
	 */
	void enterExprSwitchStmt(GoParser.ExprSwitchStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#exprSwitchStmt}.
	 * @param ctx the parse tree
	 */
	void exitExprSwitchStmt(GoParser.ExprSwitchStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#exprCaseClause}.
	 * @param ctx the parse tree
	 */
	void enterExprCaseClause(GoParser.ExprCaseClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#exprCaseClause}.
	 * @param ctx the parse tree
	 */
	void exitExprCaseClause(GoParser.ExprCaseClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#exprSwitchCase}.
	 * @param ctx the parse tree
	 */
	void enterExprSwitchCase(GoParser.ExprSwitchCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#exprSwitchCase}.
	 * @param ctx the parse tree
	 */
	void exitExprSwitchCase(GoParser.ExprSwitchCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeSwitchStmt}.
	 * @param ctx the parse tree
	 */
	void enterTypeSwitchStmt(GoParser.TypeSwitchStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeSwitchStmt}.
	 * @param ctx the parse tree
	 */
	void exitTypeSwitchStmt(GoParser.TypeSwitchStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeSwitchGuard}.
	 * @param ctx the parse tree
	 */
	void enterTypeSwitchGuard(GoParser.TypeSwitchGuardContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeSwitchGuard}.
	 * @param ctx the parse tree
	 */
	void exitTypeSwitchGuard(GoParser.TypeSwitchGuardContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeCaseClause}.
	 * @param ctx the parse tree
	 */
	void enterTypeCaseClause(GoParser.TypeCaseClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeCaseClause}.
	 * @param ctx the parse tree
	 */
	void exitTypeCaseClause(GoParser.TypeCaseClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeSwitchCase}.
	 * @param ctx the parse tree
	 */
	void enterTypeSwitchCase(GoParser.TypeSwitchCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeSwitchCase}.
	 * @param ctx the parse tree
	 */
	void exitTypeSwitchCase(GoParser.TypeSwitchCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeList}.
	 * @param ctx the parse tree
	 */
	void enterTypeList(GoParser.TypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeList}.
	 * @param ctx the parse tree
	 */
	void exitTypeList(GoParser.TypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#selectStmt}.
	 * @param ctx the parse tree
	 */
	void enterSelectStmt(GoParser.SelectStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#selectStmt}.
	 * @param ctx the parse tree
	 */
	void exitSelectStmt(GoParser.SelectStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#commClause}.
	 * @param ctx the parse tree
	 */
	void enterCommClause(GoParser.CommClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#commClause}.
	 * @param ctx the parse tree
	 */
	void exitCommClause(GoParser.CommClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#commCase}.
	 * @param ctx the parse tree
	 */
	void enterCommCase(GoParser.CommCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#commCase}.
	 * @param ctx the parse tree
	 */
	void exitCommCase(GoParser.CommCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#recvStmt}.
	 * @param ctx the parse tree
	 */
	void enterRecvStmt(GoParser.RecvStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#recvStmt}.
	 * @param ctx the parse tree
	 */
	void exitRecvStmt(GoParser.RecvStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(GoParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(GoParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#forClause}.
	 * @param ctx the parse tree
	 */
	void enterForClause(GoParser.ForClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#forClause}.
	 * @param ctx the parse tree
	 */
	void exitForClause(GoParser.ForClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#rangeClause}.
	 * @param ctx the parse tree
	 */
	void enterRangeClause(GoParser.RangeClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#rangeClause}.
	 * @param ctx the parse tree
	 */
	void exitRangeClause(GoParser.RangeClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#goStmt}.
	 * @param ctx the parse tree
	 */
	void enterGoStmt(GoParser.GoStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#goStmt}.
	 * @param ctx the parse tree
	 */
	void exitGoStmt(GoParser.GoStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#type_}.
	 * @param ctx the parse tree
	 */
	void enterType_(GoParser.Type_Context ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#type_}.
	 * @param ctx the parse tree
	 */
	void exitType_(GoParser.Type_Context ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeArgs}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgs(GoParser.TypeArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeArgs}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgs(GoParser.TypeArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(GoParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(GoParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeLit}.
	 * @param ctx the parse tree
	 */
	void enterTypeLit(GoParser.TypeLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeLit}.
	 * @param ctx the parse tree
	 */
	void exitTypeLit(GoParser.TypeLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(GoParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(GoParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#arrayLength}.
	 * @param ctx the parse tree
	 */
	void enterArrayLength(GoParser.ArrayLengthContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#arrayLength}.
	 * @param ctx the parse tree
	 */
	void exitArrayLength(GoParser.ArrayLengthContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#elementType}.
	 * @param ctx the parse tree
	 */
	void enterElementType(GoParser.ElementTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#elementType}.
	 * @param ctx the parse tree
	 */
	void exitElementType(GoParser.ElementTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#pointerType}.
	 * @param ctx the parse tree
	 */
	void enterPointerType(GoParser.PointerTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#pointerType}.
	 * @param ctx the parse tree
	 */
	void exitPointerType(GoParser.PointerTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#interfaceType}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceType(GoParser.InterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#interfaceType}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceType(GoParser.InterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#sliceType}.
	 * @param ctx the parse tree
	 */
	void enterSliceType(GoParser.SliceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#sliceType}.
	 * @param ctx the parse tree
	 */
	void exitSliceType(GoParser.SliceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#mapType}.
	 * @param ctx the parse tree
	 */
	void enterMapType(GoParser.MapTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#mapType}.
	 * @param ctx the parse tree
	 */
	void exitMapType(GoParser.MapTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelType(GoParser.ChannelTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelType(GoParser.ChannelTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#methodSpec}.
	 * @param ctx the parse tree
	 */
	void enterMethodSpec(GoParser.MethodSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#methodSpec}.
	 * @param ctx the parse tree
	 */
	void exitMethodSpec(GoParser.MethodSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#functionType}.
	 * @param ctx the parse tree
	 */
	void enterFunctionType(GoParser.FunctionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#functionType}.
	 * @param ctx the parse tree
	 */
	void exitFunctionType(GoParser.FunctionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#signature}.
	 * @param ctx the parse tree
	 */
	void enterSignature(GoParser.SignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#signature}.
	 * @param ctx the parse tree
	 */
	void exitSignature(GoParser.SignatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#result}.
	 * @param ctx the parse tree
	 */
	void enterResult(GoParser.ResultContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#result}.
	 * @param ctx the parse tree
	 */
	void exitResult(GoParser.ResultContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(GoParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(GoParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#parameterDecl}.
	 * @param ctx the parse tree
	 */
	void enterParameterDecl(GoParser.ParameterDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#parameterDecl}.
	 * @param ctx the parse tree
	 */
	void exitParameterDecl(GoParser.ParameterDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GoParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GoParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(GoParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(GoParser.PrimaryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#conversion}.
	 * @param ctx the parse tree
	 */
	void enterConversion(GoParser.ConversionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#conversion}.
	 * @param ctx the parse tree
	 */
	void exitConversion(GoParser.ConversionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(GoParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(GoParser.OperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(GoParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(GoParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#basicLit}.
	 * @param ctx the parse tree
	 */
	void enterBasicLit(GoParser.BasicLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#basicLit}.
	 * @param ctx the parse tree
	 */
	void exitBasicLit(GoParser.BasicLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(GoParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(GoParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#operandName}.
	 * @param ctx the parse tree
	 */
	void enterOperandName(GoParser.OperandNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#operandName}.
	 * @param ctx the parse tree
	 */
	void exitOperandName(GoParser.OperandNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#qualifiedIdent}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedIdent(GoParser.QualifiedIdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#qualifiedIdent}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedIdent(GoParser.QualifiedIdentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#compositeLit}.
	 * @param ctx the parse tree
	 */
	void enterCompositeLit(GoParser.CompositeLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#compositeLit}.
	 * @param ctx the parse tree
	 */
	void exitCompositeLit(GoParser.CompositeLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#literalType}.
	 * @param ctx the parse tree
	 */
	void enterLiteralType(GoParser.LiteralTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#literalType}.
	 * @param ctx the parse tree
	 */
	void exitLiteralType(GoParser.LiteralTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#literalValue}.
	 * @param ctx the parse tree
	 */
	void enterLiteralValue(GoParser.LiteralValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#literalValue}.
	 * @param ctx the parse tree
	 */
	void exitLiteralValue(GoParser.LiteralValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#elementList}.
	 * @param ctx the parse tree
	 */
	void enterElementList(GoParser.ElementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#elementList}.
	 * @param ctx the parse tree
	 */
	void exitElementList(GoParser.ElementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#keyedElement}.
	 * @param ctx the parse tree
	 */
	void enterKeyedElement(GoParser.KeyedElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#keyedElement}.
	 * @param ctx the parse tree
	 */
	void exitKeyedElement(GoParser.KeyedElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(GoParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(GoParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(GoParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(GoParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#structType}.
	 * @param ctx the parse tree
	 */
	void enterStructType(GoParser.StructTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#structType}.
	 * @param ctx the parse tree
	 */
	void exitStructType(GoParser.StructTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#fieldDecl}.
	 * @param ctx the parse tree
	 */
	void enterFieldDecl(GoParser.FieldDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#fieldDecl}.
	 * @param ctx the parse tree
	 */
	void exitFieldDecl(GoParser.FieldDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#string_}.
	 * @param ctx the parse tree
	 */
	void enterString_(GoParser.String_Context ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#string_}.
	 * @param ctx the parse tree
	 */
	void exitString_(GoParser.String_Context ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#embeddedField}.
	 * @param ctx the parse tree
	 */
	void enterEmbeddedField(GoParser.EmbeddedFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#embeddedField}.
	 * @param ctx the parse tree
	 */
	void exitEmbeddedField(GoParser.EmbeddedFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#functionLit}.
	 * @param ctx the parse tree
	 */
	void enterFunctionLit(GoParser.FunctionLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#functionLit}.
	 * @param ctx the parse tree
	 */
	void exitFunctionLit(GoParser.FunctionLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(GoParser.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(GoParser.IndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#slice_}.
	 * @param ctx the parse tree
	 */
	void enterSlice_(GoParser.Slice_Context ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#slice_}.
	 * @param ctx the parse tree
	 */
	void exitSlice_(GoParser.Slice_Context ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#typeAssertion}.
	 * @param ctx the parse tree
	 */
	void enterTypeAssertion(GoParser.TypeAssertionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#typeAssertion}.
	 * @param ctx the parse tree
	 */
	void exitTypeAssertion(GoParser.TypeAssertionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(GoParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(GoParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#methodExpr}.
	 * @param ctx the parse tree
	 */
	void enterMethodExpr(GoParser.MethodExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#methodExpr}.
	 * @param ctx the parse tree
	 */
	void exitMethodExpr(GoParser.MethodExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#eos}.
	 * @param ctx the parse tree
	 */
	void enterEos(GoParser.EosContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#eos}.
	 * @param ctx the parse tree
	 */
	void exitEos(GoParser.EosContext ctx);
}