package almond.kernel

import almond.kernel.install.Install
import utest._

object CopyLauncherTests extends TestSuite {

  val tests = Tests {

    "java -jar" - {
      val cmd = List("java", "-jar", "foo", "arg1", "arg2")
      val res = Install.launcherPos(cmd)
      val expectedRes = Some(2)
      assert(res == expectedRes)
    }

    "java -jar with options" - {
      val cmd = List("java", "-Xmx15T", "-Dmode=lightweight", "-jar", "foo", "arg1", "arg2")
      val res = Install.launcherPos(cmd)
      val expectedRes = Some(4)
      assert(res == expectedRes)
    }

    "java -cp: nope" - {
      val cmd = List("java", "-cp", "foo:other", "arg1", "arg2")
      val res = Install.launcherPos(cmd)
      val expectedRes = None
      assert(res == expectedRes)
    }

    "no java: nope" - {
      val cmd = List("zava", "-cp", "foo:other", "arg1", "arg2")
      val res = Install.launcherPos(cmd)
      val expectedRes = None
      assert(res == expectedRes)
    }

  }

}
