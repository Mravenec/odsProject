import { useState, useEffect } from 'react';
import { objetivo16Service } from '../services/objetivo16Service';

export const useObjetivo16 = () => {
  const [data, setData] = useState({});
  const [loading, setLoading] = useState({});
  const [error, setError] = useState({});

  const fetchData = async (indicator) => {
    setLoading(prev => ({ ...prev, [indicator]: true }));
    setError(prev => ({ ...prev, [indicator]: null }));
    
    try {
      const methodName = `getIndicador_${indicator}`;
      const result = await objetivo16Service[methodName]();
      setData(prev => ({ ...prev, [indicator]: result }));
    } catch (err) {
      setError(prev => ({ ...prev, [indicator]: err.message }));
    } finally {
      setLoading(prev => ({ ...prev, [indicator]: false }));
    }
  };

  const fetchAllData = async () => {
    const indicators = [
      '16_1_1', '16_1_2', '16_1_3', '16_1_4', '16_2_1', '16_2_2',
      '16_2_3', '16_3_1', '16_3_2', '16_3_3', '16_4_1', '16_4_2',
      '16_5_1', '16_5_2', '16_6_1', '16_6_2', '16_7_1', '16_7_2',
      '16_8_1', '16_9_1', '16_10_1', '16_10_2', '16_a_1', '16_b_1'
    ];

    await Promise.all(indicators.map(indicator => fetchData(indicator)));
  };

  return {
    data,
    loading,
    error,
    fetchData,
    fetchAllData
  };
};
