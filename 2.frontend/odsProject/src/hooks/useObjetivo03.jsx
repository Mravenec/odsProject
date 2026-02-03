import { useState, useEffect } from 'react';
import { objetivo03Service } from '../services/objetivo03Service';

export const useObjetivo03 = () => {
  const [data, setData] = useState({});
  const [loading, setLoading] = useState({});
  const [error, setError] = useState({});

  const fetchData = async (indicator) => {
    setLoading(prev => ({ ...prev, [indicator]: true }));
    setError(prev => ({ ...prev, [indicator]: null }));
    
    try {
      const methodName = `getIndicador_${indicator}`;
      const result = await objetivo03Service[methodName]();
      setData(prev => ({ ...prev, [indicator]: result }));
    } catch (err) {
      setError(prev => ({ ...prev, [indicator]: err.message }));
    } finally {
      setLoading(prev => ({ ...prev, [indicator]: false }));
    }
  };

  const fetchAllData = async () => {
    const indicators = [
      '3_1_1', '3_1_2', '3_2_1', '3_2_2', '3_3_1', '3_3_2',
      '3_3_3', '3_3_4', '3_3_5', '3_4_1', '3_4_2',
      '3_5_1', '3_5_2', '3_6_1', '3_7_1', '3_7_2',
      '3_8_1', '3_8_2', '3_9_1', '3_9_2', '3_9_3',
      '3_a_1', '3_b_1', '3_b_2', '3_b_3', '3_c_1',
      '3_d_1', '3_d_2'
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
