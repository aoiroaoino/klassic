package com.github.klassic

/**
 * @author Kota Mizushima
 */

sealed abstract class AstNode {
  val location: AstNode.Location
}

object AstNode {
  sealed abstract class Location {
    def format: String
  }
  case class SourceLocation(line: Int, column: Int) extends Location {
    def format: String = s"${line}, ${column} :"
  }
  case object NoLocation extends Location {
    def format: String = s"? :"
  }

  sealed trait IntegerSuffix

  case object ByteSuffix extends IntegerSuffix

  case object ShortSuffix extends IntegerSuffix

  case object LongSuffix extends IntegerSuffix

  sealed trait FloatSuffix

  case object FloatSuffix extends FloatSuffix

  case class Program(val location: Location, imports: List[Import], block: Block)

  case class Import(val location: Location, simpleName: String, fqcn: String)

  case class Block(val location: Location, expressions: List[AstNode]) extends AstNode

  case class IfExpression(val location: Location, condition: AstNode, thenExpression: AstNode, elseExpression: AstNode) extends AstNode

  case class ForeachExpression(val location: Location, name: String, collection: AstNode, body: AstNode) extends AstNode

  case class BinaryExpression(val location: Location, operator: Operator, lhs: AstNode, rhs: AstNode) extends AstNode

  case class WhileExpression(val location: Location, condition: AstNode, body: AstNode) extends AstNode

  case class MinusOp(val location: Location, operand: AstNode) extends AstNode

  case class PlusOp(val location: Location, operand: AstNode) extends AstNode

  case class StringNode(val location: Location, value: String) extends AstNode

  case class IntNode(val location: Location, value: Int) extends AstNode

  case class LongNode(val location: Location, value: Long) extends AstNode

  case class ShortNode(val location: Location, value: Short) extends AstNode

  case class ByteNode(val location: Location, value: Byte) extends AstNode

  case class BooleanNode(val location: Location, value: Boolean) extends AstNode

  case class DoubleNode(val location: Location, value: Double) extends AstNode

  case class FloatNode(val location: Location, value: Float) extends AstNode

  case class Identifier(val location: Location, name: String) extends AstNode

  case class Assignment(val location: Location, variable: String, value: AstNode) extends AstNode

  case class ValDeclaration(val location: Location, variable: String, description: Option[TypeDescription], value: AstNode, immutable: Boolean) extends AstNode

  case class FunctionLiteral(val location: Location, params: List[FormalParameter], proc: AstNode) extends AstNode

  case class FunctionDefinition(val location: Location, name: String, func: FunctionLiteral, cleanup: Option[AstNode]) extends AstNode

  case class FunctionCall(val location: Location, func: AstNode, params: List[AstNode]) extends AstNode

  case class ListLiteral(val location: Location, elements: List[AstNode]) extends AstNode

  case class MapLiteral(val location: Location, elements: List[(AstNode, AstNode)]) extends AstNode

  case class NewObject(val location: Location, className: String, params: List[AstNode]) extends AstNode

  case class MethodCall(val location: Location, self: AstNode, name: String, params: List[AstNode]) extends AstNode
}