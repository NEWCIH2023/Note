
[TOC]

## WireShark的基础知识

### 界面使用


+ Frame
	+ 物理层的数据帧概况
+ Ethernet II
    + 数据链路层以太网帧头部信息
+ Internet Protocol Version 4
    + 互联网层IP包头部信息
+ Transmission Control Protocol
    + 传输层的数据段头部信息
+ Hypertext Transfer Protocol
    + 应用层的信息


### 各帧、包和段内展开的内容

+ **物理层的数据帧概况**
> Frame 5: 268 bytes on write (2144 bits), 268 bytes captured (2144 bits) on interface 0        # 5号帧，线路268字节，实际捕获268字节
  Interface 0
    Interface id: 0                                                                             # 接口id
    Encapsulation type: Ethernet (1)                                                            # 封装类型
    Arrival Time: Jun 11, 2014 09:12:18.469086000 中国标准时间                                  # 捕获日期和时间
    [Time shift for this packet: 0.00000000 seconds]
    Epoch Time: 1402449138.469086000 seconds
    [Time delta from previous captured frame: 0.025257000 seconds]                              # 此包与前一包的时间间隔
    [Time since reference or first frame: 0.537138000 seconds]                                  # 此包与第一帧的时间间隔
    Frame Number: 5                                                                             # 帧序号
    Frame Length: 268 bytes (2144 bits)                                                         # 帧长度
    Capture Length: 268 bytes (2144 bits)                                                       # 捕获长度
    [Frame is marked: False]                                                                    # 此帧是否做了标记：否
    [Frame is ignored: False]                                                                   # 此帧是否被忽略：否
    [Protocols in frame: eth:ip:tcp:http]                                                       # 帧内封装的协议层次结构
    [Number of per-protocol-data: 2]
    [Hypertext Transfer Protocol, key 0]
    [Transmission Control Protocol, key 0]                                                      
    [Coloring Rule Name: HTTP]                                                                  # 着色标记的协议名称
    [Coloring Rule String: http || tcp.port == 80]                                              # 着色规则显示的字符串


+ 数据链路层以太网帧头部信息
> Ethernet II, Src: Giga-Byt_c8:4c:89 (1c:6f:65:c8:4c:89), Dst: Tp-LinkT_f9:3c:c0 (6c:e8:73:f9:3c:c0)
Destination: Tp-LinkT_f9:3c:c0 (6c:e8:73:f9:3c:c0)
    Source: Giga-Byt_c8:4c:89 (1c:6f:65:c8:4c:89)               # 目标MAC地址
    Type: IP (0x0800)                                           # 源MAC地址

+ 互联网层IP包头部信息
> Internet Protocol Version 4, Src: 192.168.0.104 (192.168.0.104), Dst: 61.182.140.146 (61.182.140.146)
  Version: 4                                                    # 互联网协议 IPv4
    Header length: 20 bytes                                     # IP包头部长度
    Differentiated Services Field: 0x00 (DSCP 0x00: Default; ECN: 0x00:Not-ECT (Not ECN-Capable Transport))     # 差分服务字段
    Total Length: 254                                           # IP包的总长度
    Identification: 0x5bb5 (23477)                              # 标志字段
    Flags: 0x02 (Don't Fragment)                                # 标记字段
    Fragment offset: 0                                          # 分的偏移量
    Time to live: 64                                            # 生存期TTL
    Protocol: TCP (6)                                           # 此包内封装的上层协议为TCP
    Header checksum: 0x52ec [validation disabled]               # 头部数据的校验和
    Source: 192.168.0.104 (192.168.0.104)                       # 源IP地址
    Destination: 61.182.140.146 (61.182.140.146)                # 目的IP地址

+ 传输层TCP数据段头部信息
> Transmission Control Protocol, Src Port: 51833 (51833), Dst Port: http (80), Seq: 1, Ack: 1, Len: 214
  Source port: 51833 (51833)                                    # 源端口号
    Destination port: http (80)                                 # 目标端口号
    Sequence number: 1 (relative sequence number)               # 序列号 (相对序列号)
    [Next sequence number: 215 (relative sequence number)]      # 下一个序列号
    Acknowledgment number: 1   (relative sequence number)       # 确认序列号
    Header length: 20 bytes                                     # 头部长度
    Flags: 0x018 (PSH, ACK)                                     # TCP标记字段
    Window size value: 64800                                    # 流量控制的窗口大小
    Checksum: 0x677e [validation disabled]                      # TCP数据段的校验和







