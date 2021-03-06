package com.github.klassic.macro_peg

import org.scalatest.{DiagrammedAssertions, FunSpec}
import Runner._
import EvaluationResult.Success

class MacroPegCallByValueSeqSpec extends FunSpec with DiagrammedAssertions {
  describe("Macro PEG with call by value seq example") {
    it("simple") {
      val results = evalGrammar(
        """
          |S = F("a", "b", "c"); F(A, B, C) = "abc";
     """.stripMargin,
        Seq("abcabc"),
        EvaluationStrategy.CallByValueSeq
      )
      assertResult(Seq(Success("")))(results)
    }

    it("xml") {
      val results = evalGrammar(
        """
          |S = F("<", [a-zA-Z_]+, ">"); F(LT, N, GT) = F("<", [a-zA-Z_]+, ">")* LT "/" N GT;
        """.stripMargin,
        Seq( "<a></a>"),
        EvaluationStrategy.CallByValueSeq
      )
      assertResult(Seq(Success("")))(results)
    }

  }
}

