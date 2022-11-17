import java.util.LinkedList;
import java.util.List;

/**
 * Author: ������
 * Date: 2015-06-24
 * Time: 19:33
 * Declaration: All Rights Reserved !!!
 */
public class Solution {
    /**
     * <pre>
     * Given a set of non-overlapping intervals, insert a new interval into the intervals
     * (merge if necessary).
     *
     * You may assume that the intervals were initially sorted according to their start times.
     *
     * Example 1:
     * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
     *
     * Example 2:
     * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
     *
     * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
     *
     * ��Ŀ���⣺
     * ����һϵ�зǸ��ǵ����䣬����һ���µ����䣬�б�Ҫ��ʱ���������ϲ�
     * ���俪ʼ������ʼʱ����кϲ���
     *
     * ����˼·��
     * ���ԭ��������Ȳ�������С�Ͳ�����������������������ص������²������䣬
     * �����������С��ԭ�������䣬�Ȳ���������䣬����Ӵ������
     * </pre>
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        // �������ļ���
        List<Interval> result = new LinkedList<>();

        // ���뼯�ǿ�
        if (intervals != null) {
            // ����Ԫ��
            for (Interval item : intervals) {
                // newInterval == null ��ʾ����������Ѿ���������
                // ���Ȳ�������С���������������
                if (newInterval == null || item.end < newInterval.start) {
                    result.add(item);
                }
                // ���Ȳ���������������������У�ͬʱ������������������
                else if (item.start > newInterval.end) {
                    result.add(newInterval);
                    result.add(item);
                    newInterval = null;
                }
                // �����������ص������²�������
                else {
                    newInterval.start = Math.min(newInterval.start, item.start);
                    newInterval.end = Math.max(newInterval.end, item.end);
                }
            }
        }

        // �����������ǿ�˵���������仹δ������
        if (newInterval != null) {
            result.add(newInterval);
        }

        return result;
    }
}
