package com.htdz.liteguardian.message;

import java.util.HashMap;
import java.util.Map;

public class CastelMessage_0x2003 extends CastelMessage {
	// 获取消息体对象
	@Override
	public  Map<Integer, Object> getMsgBodyMap() {
		Map<Integer, Object> returnMsgBodyMap = new HashMap<Integer, Object>();

		short msgBodyLength = msgAnalyze.getMsgHeadLength();
		if ((msgAnalyze.checkStatus) && (msgBodyLength > 0) && (msgBodyLength == msgAnalyze.msgBody.length) && (msgAnalyze.msgBody != null)) {
			int i = 0;
			while (i < msgAnalyze.msgBody.length) {
				// 获取消息体一条消息KEY
				int keyStartIndex = i;
				short key = msgAnalyze.byte2short(new byte[] {
						msgAnalyze.msgBody[keyStartIndex].byteValue(),
						msgAnalyze.msgBody[(keyStartIndex + 1)].byteValue() });

				// 获取消息体一条消息长度
				int lengthStartIndex = i + 2;
				short length = msgAnalyze
						.byte2short(new byte[] {
								msgAnalyze.msgBody[lengthStartIndex]
										.byteValue(),
								msgAnalyze.msgBody[(lengthStartIndex + 1)]
										.byteValue() });

				// 获取消息体一条消息内容的BYTE数组
				int valueStartIndex = i + 4;
				int valueEndIndex = i + 4 + length - 1;
				byte[] msgValue = new byte[length];
				for (int k = 0; k <= length - 1; k++) {
					if (length>1 && msgAnalyze.msgBody[valueStartIndex + k].byteValue() == (byte) 0) {
						break;
					}

					msgValue[k] = msgAnalyze.msgBody[valueStartIndex + k].byteValue();
				}

				Object value=(int)msgValue[0];
				
				returnMsgBodyMap.put((int) key, value);
				i = valueEndIndex + 1;
			}
		}

		return returnMsgBodyMap;
	}
}
